package cn.tianff.elephant.algorithm.predicting;

import cn.tianff.elephant.algorithm.clustering.Clusterers;
import cn.tianff.elephant.model.location.GPSGridLocation;
import cn.tianff.elephant.model.location.GPSPoint;
import cn.tianff.elephant.model.tracking.Result;
import cn.tianff.elephant.model.tracking.TimePeriod;
import cn.tianff.elephant.model.tracking.TrackPoint;
import cn.tianff.elephant.model.tracking.TrackResult;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterer;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Non-ThreadSafe
 */
public class ATPDC implements Predicts {

    private Property property;

    /**
     * Result 4 this calculation
     */
    private Result mResult;

    /**
     * May not exist;
     * Result of last calculation
     */
    private Result oldResult;

    private List<GPSPoint> data;

    /**
     * 由GPSPoint处理而来的移动电集合
     */
    private List<GPSGridLocation> movingLocationData;

    /**
     * 按六个时间段划分为六个相应的移动点子集
     */
    private Map<TimePeriod, List<GPSGridLocation>> movingPoints;

    private static final Property defaultProperty = createDefaultProperty();

    public ATPDC() {
        this(defaultProperty);
    }

    public ATPDC(Property property) {
        this.property = property;
        mResult = new TrackResult();
    }

    @Override
    public void setProperty(Property property) {

    }

    @Override
    public void accept(List<GPSPoint> data) {
        processData(data);
    }

    public void accept2(List<GPSGridLocation> data) {
        this.movingLocationData = data;
        movingPoints = data.stream()
                .collect(Collectors.groupingBy(GPSGridLocation::getTimePeriod));
    }

    @Override
    public void accept(Result result, List<GPSPoint> data) {
        this.oldResult = result;
        // TODO: 2018/4/8

    }

    @Override
    @SuppressWarnings("unchecked")
    public Future<Result> predict() {
        //todo: check 数据完整性
        //检查movingPoints是否有合法数据

        return CompletableFuture.supplyAsync(this::mPredict);
    }

    @SuppressWarnings("unchecked")
    private Result mPredict() {
        //1.将用户一天完整的移动点集Setmov 按六个时间段划分为六个相应的移动点子集，
        //分别在各移动点子集上使用 密度聚类算法 对移动点进行聚类形成新 轨迹簇 集合
        //并计算相应的轨迹点及其影响区域，执行步骤2

        //获取一个当前配置的聚类方案
        Class<? extends Clusterer> clazz = property.getClusterType();
        Clusterer<GPSGridLocation> clusterer = Clusterers.newClustererBy(clazz, GPSGridLocation.class);

        //循环6个时间段的集合，对每一个集合做聚类
        movingPoints.forEach((time, list) -> {
            //聚类
            List<Cluster<GPSGridLocation>> clusteringResult = (List<Cluster<GPSGridLocation>>) clusterer.cluster(list);
            mResult.addClusters4EachTimePeriod(time, clusteringResult);

            //并计算相应的轨迹点及其影响区域，执行步骤2
            //轨迹点是轨迹簇中全部移动点位置坐标的加权平均点,轨迹点的影响区域是以轨迹点为中心的圆形区域
            List<TrackPoint> trackPoints = clusteringResult.stream()
                    .map(this::calculateEffect)
                    .collect(Collectors.toList());

            mResult.addTrackPoint4EachTimePeriod(time, trackPoints);
        });

        //2. 如果Trajold 为空，依次计算各时间段内轨迹点的预测概率并构建用户的移动轨迹
        //预测模型TM；如果Trajold 非空，则执行步骤3

        if (oldResult == null) {
            Map<TimePeriod, List<TrackPoint>> map = mResult.getClusterTrackPoints();
//            final Map<TimePeriod, Map<TrackPoint, Double>> probabilities = new HashMap<>();
            map.forEach((time, ll) -> calculateProbability(ll));
            return mResult;
//            mResult.setProbabilities(probabilities);
        } else {
            /**
             * 合并Trajold 和Trajnew 中相应时间段内的轨迹簇并更新轨迹点及其影响区域。
             * 根据轨迹点相似度的定义，依次在Trajold 中查找与Trajnew 中每一个新轨迹簇最相似的旧轨
             * 迹簇，如果相似度大于或等于阈值smod，则将新轨迹簇与旧轨迹簇合并，然后从Trajnew
             * 中删除已经被合并的新轨迹簇；
             */

            Map<TimePeriod, List<TrackPoint>> oldTime2TrackPoint = oldResult.getClusterTrackPoints();
            Map<TimePeriod, List<TrackPoint>> newTime2TrackPoint = mResult.getClusterTrackPoints();

            oldTime2TrackPoint.forEach((time, list) -> {

                List<TrackPoint> ll = newTime2TrackPoint.get(time);

                list.forEach(p -> {
                    //descend
                    ll.sort((p1, p2) -> (int) (calculateSimilarity(p, p2) - calculateSimilarity(p, p1)));

                    if (Objects.nonNull(ll.get(0))
                            // TODO: 2018/4/12 优化代码：
                            && calculateSimilarity(p, ll.get(0)) >= property.getSimilarityDuringModeling()) {

                        //合并到oldResult并删除newResult中的数据
                        // TODO: 2018/4/11
                        oldResult.addClusters4EachTimePeriod(time, Collections.singletonList(ll.get(0).getCluster()));
                        ll.remove(0);
                    }
                });
            });

            //删除Trajold 中各时间段内的无效轨迹簇和轨迹点

            Map<TimePeriod, List<TrackPoint>> time2TrackPoints = processClusters(oldResult.getClusters4EachTimePeriod());
            time2TrackPoints.forEach((time, trackPoints) -> {

                trackPoints.sort((p1, p2) -> (int) (p2.getProbability() - p1.getProbability()));

                if (trackPoints.size() > property.getDeleteFromClusterLimit()) {
                    /**
                     * 如果某个时间段内的轨迹点个数大于或等于阈值t，则保留t个预测概率较
                     大的轨迹点和轨迹簇不被删除，将预测概率小于pmin 并且保留次数大于nkep 的轨迹点和相
                     应的轨迹簇删除
                     */
                    List<TrackPoint> removed = trackPoints.subList(property.getDeleteFromClusterLimit(), trackPoints.size());

                    //删除轨迹点
                    trackPoints.removeAll(removed);
//                    trackPoints = trackPoints.subList(0, property.getDeleteFromClusterLimit());
                    //删除轨迹簇
                    removed.forEach(p ->
                            oldResult.getClusters4EachTimePeriod().get(time).remove(p.getCluster())
                    );
                }
            });

            //将newResult中未进行合并操作的新轨迹簇加入到oldResult 中并更新相应的轨迹点及
            //其影响区域，依次计算轨迹点的预测概率并构建用户的移动轨迹预测模型TM
            mResult.getClusters4EachTimePeriod().forEach((time, list) ->
                    oldResult.addClusters4EachTimePeriod(time, list));

            //构建输出
            processClusters(oldResult.getClusters4EachTimePeriod());
            return oldResult;
        }
    }


    private Map<TimePeriod, List<TrackPoint>> processClusters(Map<TimePeriod, List<Cluster<GPSGridLocation>>> map) {
        Map<TimePeriod, List<TrackPoint>> m = new HashMap<>();
        map.forEach((time, list) -> {
            List<TrackPoint> trackPoints = list.stream()
                    .map(this::calculateEffect)
                    .collect(Collectors.toList());
            calculateProbability(trackPoints);
            m.put(time, trackPoints);
        });
        return m;
    }

    private double calculateSimilarity(TrackPoint p1, TrackPoint p2) {


        return 0;
    }

    private TrackPoint calculateEffect(Cluster<GPSGridLocation> cluster) {

        int sum = cluster.getPoints().stream()
                .mapToInt(GPSGridLocation::getContains)
                .sum();

        double gridX = cluster.getPoints().stream()
                .mapToDouble(p -> p.getContains() * p.getGridX())
                .map(d -> d / sum)
                .sum();
        double gridY = cluster.getPoints().stream()
                .mapToDouble(p -> p.getContains() * p.getGridY())
                .map(d -> d / sum)
                .sum();

        TrackPoint trackPoint = new TrackPoint(cluster);
        trackPoint.setLocation(gridX, gridY);

        // fixme: 2018/4/7 影响区域
        double radius = 0;
        if (sum >= property.getMaxNumInCluster()) {
            radius = property.getMaxClusterInfectRadius();
        } else if (sum <= property.getMinNumInCluster()) {
            radius = property.getMinClusterInfectRadius();
        } else {
            radius = sum * property.getMinClusterInfectRadius() / property.getMaxClusterInfectRadius();
        }
        trackPoint.setRadius(radius);

        return trackPoint;
    }

    private void calculateProbability(List<TrackPoint> ll) {
        int sum = ll.stream()
                .mapToInt(TrackPoint::getWeight)
                .sum();

        ll.forEach(p -> {
            p.setProbability(p.getWeight() / sum);
        });
    }

    /**
     * transform GpsPoint to GridLocation
     * put data into field movingLocationData
     * todo: 待实现
     */
    private void processData(List<GPSPoint> points) {
        //GPS经纬度转换到能够参与运算的Grid_x,Grid_y


        //将处理过的数据按照时间段划分放到movingPoints
    }

    private static Property createDefaultProperty() {


        return null;
    }
}

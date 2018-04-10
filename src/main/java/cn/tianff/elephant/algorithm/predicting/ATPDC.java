package cn.tianff.elephant.algorithm.predicting;

import cn.tianff.elephant.algorithm.clustering.Clusterers;
import cn.tianff.elephant.model.location.GPSGridLocation;
import cn.tianff.elephant.model.location.GPSPoint;
import cn.tianff.elephant.model.tracking.Result;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterer;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Non-ThreadSafe
 */
public class ATPDC implements Predicts {

    private Property property;

    private Result mResult;

    private List<GPSPoint> data;

    /**
     * 由GPSPoint处理而来的移动电集合
     */
    private List<GPSGridLocation> movingLocationData;

    /**
     * 按六个时间段划分为六个相应的移动点子集
     */
    private Set<Set<GPSGridLocation>> movingPoints;

    private static final Property defaultProperty = createDefaultProperty();

    public ATPDC() {
        this(defaultProperty);
    }

    public ATPDC(Property property) {
        this.property = property;
    }

    @Override
    public void setProperty(Property property) {

    }

    @Override
    public void accept(List<GPSPoint> data) {
        processData(data);
    }

    @Override
    public void accept(Result result, List<GPSPoint> data) {

    }

    @Override
    public Future<Result> predict() {

        //check 数据完整性


        Future f = new CompletableFuture();
        //1.将用户一天完整的移动点集Setmov 按六个时间段划分为六个相应的移动点子集，
        //分别在各移动点子集上使用 密度聚类算法 对移动点进行聚类形成新 轨迹簇 集合
        //Trajnew，并计算相应的轨迹点及其影响区域，执行步骤2
        Class<? extends Cluster> clazz = property.getClusterType();
        Clusterer<GPSGridLocation> clusterer = Clusterers.newClustererBy(clazz);
        // TODO: 2018/4/4 @SupressWarning
        List clusters = clusterer.cluster(movingLocationData);


        //2. 如果Trajold 为空，依次计算各时间段内轨迹点的预测概率并构建用户的移动轨迹
        //预测模型TM；如果Trajold 非空，则执行步骤3

        return null;
    }

    /**
     * transform GpsPoint to GridLocation
     * put data into field movingLocationData
     */
    private void processData(List<GPSPoint> points) {

        //GPS经纬度转换到能够参与运算的Grid_x,Grid_y


        //将处理过的数据按照时间段划分
    }

    private static Property createDefaultProperty() {


        return null;
    }
}

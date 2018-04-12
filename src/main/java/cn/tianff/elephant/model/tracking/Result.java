package cn.tianff.elephant.model.tracking;

import cn.tianff.elephant.model.location.GPSGridLocation;
import org.apache.commons.math3.ml.clustering.Cluster;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 按照时间段对结果集进行分类，每一个时间段对应一组轨迹簇，
 * 即： 每一个时间段内，都是一组聚类
 */
public interface Result {

    /**
     * 获取当前模型中每一个时间段内的轨迹簇集合
     */
    Map<TimePeriod, List<Cluster<GPSGridLocation>>> getClusters4EachTimePeriod();

//    List<Cluster<GPSGridLocation>> addClusters4EachTimePeriod();

    /**
     * 获取当前模型中每一个时间段内的轨迹点
     */
    Map<TimePeriod, List<TrackPoint>> getClusterTrackPoints();
    List<TrackPoint> addTrackPoint4EachTimePeriod(TimePeriod period, List<TrackPoint> points);

//    List<TrackPoint> addTrackPoint4EachTimePeriod(TimePeriod period, List<TrackPoint> points);
    List<Cluster<GPSGridLocation>> addClusters4EachTimePeriod(TimePeriod period, List<Cluster<GPSGridLocation>> clusters);

//    /**
//     * 获取当前模型下，目标出现在某一个时间段内某一个轨迹点的概率
//     */
//    Double getProbabilities4Time(TimePeriod period, TrackPoint point);
//
//    void setProbabilities(Map<TimePeriod, Map<TrackPoint, Double>> map);

    /**
     * 获取当前模型下，建模对象出现在某个轨迹点的概率
     */
    double appearInPoint(GPSGridLocation point);

    /**
     * 获取当前模型下，建模对象在每一个轨迹点出现的概率
     */
    Map<GPSGridLocation, Double> getProbabilities();
}

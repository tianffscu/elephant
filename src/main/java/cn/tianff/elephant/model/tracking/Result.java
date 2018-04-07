package cn.tianff.elephant.model.tracking;

import cn.tianff.elephant.model.location.GPSGridLocation;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  按照时间段对结果集进行分类，每一个时间段对应一组轨迹簇，
 *  即： 每一个时间段内，都是一组聚类
 */
public interface Result {

    /**
     * 获取当前预测模型中的轨迹簇轨迹点集合
     */
    Map<TimePeriod,List<TrackPoint>> getClusterTrackPoints();

    /**
     * 获取当前模型下，建模对象出现在某个轨迹点的概率
     */
    double appearInPoint(GPSGridLocation point);

    /**
     * 获取当前模型下，建模对象在每一个轨迹点出现的概率
     */
    Map<GPSGridLocation, Double> getProbabilities();
}

package cn.tianff.elephant.model.tracking;

import java.util.Map;
import java.util.Set;

public interface Result {

    /**
     * 获取当前预测模型中的轨迹簇集合
     */
    Set<Set<TrackPoint>> getClusters();

    /**
     * 获取当前模型下，建模对象出现在某个轨迹点的概率
     */
    double appearInPoint(TrackPoint point);

    /**
     * 获取当前模型下，建模对象在每一个轨迹点出现的概率
     */
    Map<TrackPoint, Double> getProbabilities();
}

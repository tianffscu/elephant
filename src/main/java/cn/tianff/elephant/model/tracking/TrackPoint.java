package cn.tianff.elephant.model.tracking;

import cn.tianff.elephant.model.location.GPSGridLocation;

/**
 * Describe a virtual center of a cluster
 * 轨迹点---> 用加权平均求得，是用户移动点的某个聚类的中心代表点
 */
public class TrackPoint {

    private GPSGridLocation virtualLocation;

    /**
     * the quantity of gps location which can be infected by the virtual localtion.
     */
    private int weight;

}

package cn.tianff.elephant.model.tracking;

import cn.tianff.elephant.model.location.GPSGridLocation;

/**
 * Describe a virtual center of a cluster
 */
public class TrackPoint {

    private GPSGridLocation virtualLocation;

    /**
     * the quantity of gps location which can be infected by the virtual localtion.
     */
    private int weight;

}

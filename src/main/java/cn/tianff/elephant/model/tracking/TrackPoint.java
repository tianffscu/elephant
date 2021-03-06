package cn.tianff.elephant.model.tracking;

import cn.tianff.elephant.model.location.GPSGridLocation;
import org.apache.commons.math3.ml.clustering.Cluster;

import java.util.Objects;

/**
 * Describe a virtual center of a cluster
 * 轨迹点---> 用加权平均求得，是用户移动点的某个聚类的中心代表点
 * 轨迹点的影响区域是以轨迹点为中心的圆形区域
 */
public class TrackPoint {

    private GPSGridLocation virtualLocation;

    /**
     * The Location-Cluster this virtual point presents
     */
    private Cluster<GPSGridLocation> cluster;

    /**
     * the quantity of gps location which can be infected by the virtual localtion.
     */
    private int weight;

    /**
     * 轨迹点影响区域的半径
     */
    private double radius;

    private double probability;

    public TrackPoint(Cluster<GPSGridLocation> cluster) {
        this.cluster = cluster;
    }

    public double getX() {
        Objects.requireNonNull(virtualLocation, "Central location value has not been set!");
        return virtualLocation.getGridX();
    }

    public double getY() {
        Objects.requireNonNull(virtualLocation, "Central location value has not been set!");
        return virtualLocation.getGridY();
    }

    public Cluster<GPSGridLocation> getCluster() {
        return cluster;
    }

    public void setCluster(Cluster<GPSGridLocation> cluster) {
        this.cluster = cluster;
    }

    public void setLocation(double x, double y) {
        virtualLocation = new GPSGridLocation(x, y);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}

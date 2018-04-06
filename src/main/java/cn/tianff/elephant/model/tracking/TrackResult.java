package cn.tianff.elephant.model.tracking;

import cn.tianff.elephant.model.location.GPSGridLocation;
import org.apache.commons.math3.ml.clustering.Cluster;

import java.io.Serializable;
import java.util.*;

public class TrackResult implements Result, Serializable {

    private Map<TimePeriod,List<Cluster<GPSGridLocation>>> clusters4EachTimePeriod;

    public TrackResult() {
        clusters4EachTimePeriod = new HashMap<>();
    }

    public List<List<Cluster<GPSGridLocation>>> getClusters4EachTimePeriod() {
//       todo: return Collections.copy(clusters4EachTimePeriod);深拷贝与浅拷贝
         return clusters4EachTimePeriod;
    }

    public void addClusters4EachTimePeriod(List<List<Cluster<GPSGridLocation>>> clusters4EachTimePeriod) {
        this.clusters4EachTimePeriod = clusters4EachTimePeriod;
    }



    @Override
    public Set<Set<TrackPoint>> getClusters() {
        return null;
    }

    @Override
    public double appearInPoint(TrackPoint point) {
        return 0;
    }

    @Override
    public Map<TrackPoint, Double> getProbabilities() {
        return null;
    }
}

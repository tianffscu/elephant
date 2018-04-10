package cn.tianff.elephant.model.tracking;

import cn.tianff.elephant.model.location.GPSGridLocation;
import org.apache.commons.math3.ml.clustering.Cluster;

import java.io.Serializable;
import java.util.*;

public class TrackResult implements Result, Serializable {

    private Map<TimePeriod, List<Cluster<GPSGridLocation>>> clusters4EachTimePeriod;

    private Map<TimePeriod, List<TrackPoint>> trackPoints;

    private Map<TimePeriod, Map<TrackPoint, Double>> probabilities;

    public TrackResult() {
        clusters4EachTimePeriod = new HashMap<>(TimePeriod.values().length);
        trackPoints = new HashMap<>(TimePeriod.values().length);
        probabilities = new HashMap<>(TimePeriod.values().length);
    }

    public Map<TimePeriod, List<Cluster<GPSGridLocation>>> getClusters4EachTimePeriod() {
//       todo: return Collections.copy(clusters4EachTimePeriod);深拷贝与浅拷贝
        return clusters4EachTimePeriod;
    }

    /**
     * 向结果模型中添加数据，如果某个时间段对应的数据不为空，方法返回之前的数据
     *
     * @Nullable
     */
    @Override
    public List<Cluster<GPSGridLocation>> addClusters4EachTimePeriod(TimePeriod period, List<Cluster<GPSGridLocation>> clusters) {
        Objects.requireNonNull(clusters);
        Objects.requireNonNull(period);

        List<Cluster<GPSGridLocation>> previous = clusters4EachTimePeriod.get(period);


        clusters4EachTimePeriod.put(period, processData(previous, clusters));

        return previous;
    }

    private List<Cluster<GPSGridLocation>> processData(List<Cluster<GPSGridLocation>> previous,
                                                       List<Cluster<GPSGridLocation>> newer) {
        if (previous == null) {
            return newer;
        } else {
            // TODO: 2018/4/7


            return null;
        }
    }

    public List<TrackPoint> addTrackPoint4EachTimePeriod(TimePeriod period, List<TrackPoint> points) {
        // TODO: 2018/4/7
        return null;
    }

    public void setProbabilities(Map<TimePeriod, Map<TrackPoint, Double>> probabilities) {
        this.probabilities = probabilities;
    }

    @Override
    public Map<TimePeriod, List<TrackPoint>> getClusterTrackPoints() {
        return trackPoints;
    }

    @Override
    public double appearInPoint(GPSGridLocation point) {
        return 0;
    }

    @Override
    public Map<GPSGridLocation, Double> getProbabilities() {
        return null;
    }

    public Double getProbabilities4Time(TimePeriod period, TrackPoint point) {
        Map<TrackPoint, Double> mm = probabilities.get(period);
        Objects.requireNonNull(mm, "Illegal TimePeriod!");
        return mm.getOrDefault(point, 0d);
    }
}

package cn.tianff.elephant.model.tracking;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class TrackResult implements Result, Serializable {


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

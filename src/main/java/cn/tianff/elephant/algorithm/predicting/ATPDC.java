package cn.tianff.elephant.algorithm.predicting;

import cn.tianff.elephant.model.location.GPSGridLocation;
import cn.tianff.elephant.model.location.GPSPoint;
import cn.tianff.elephant.model.tracking.Result;

import java.util.List;
import java.util.concurrent.Future;

public class ATPDC implements Predicts {

    private Property property;

    private Result mResult;

    private List<GPSPoint> data;

    private List<GPSGridLocation> movingLocationData;

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

        return null;
    }

    /**
     * transform GpsPoint to GridLocation
     * put data into field movingLocationData
     */
    private void processData(List<GPSPoint> points) {

    }

    private static Property createDefaultProperty() {


        return null;
    }
}

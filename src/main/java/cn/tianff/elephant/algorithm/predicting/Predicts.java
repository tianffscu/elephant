package cn.tianff.elephant.algorithm.predicting;

import cn.tianff.elephant.model.location.GPSPoint;
import cn.tianff.elephant.model.tracking.Result;

import java.util.List;
import java.util.concurrent.Future;

public interface Predicts {

    /**
     * must be called before {#predict()}
     *
     * @param property Predict algorithm properties
     */
    void setProperty(Property property);

    /**
     * Use GPS data to generate a new result
     *
     * @param data GPS points
     */
    void accept(List<GPSPoint> data);

    /**
     * Use result from the last turn and data to generate a new result
     *
     * @param result GPS points
     */
    void accept(Result result, List<GPSPoint> data);

    /**
     * main method for the algorithm
     */
    Future<Result> predict();
}

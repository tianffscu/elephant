package cn.tianff.elephant.algorithm.predicting;

import cn.tianff.elephant.model.location.GPSPoint;
import cn.tianff.elephant.model.tracking.Result;

import java.util.List;
import java.util.concurrent.Future;

public class ATPDC implements Predicts{


    @Override
    public void setProperty(Property property) {

    }

    @Override
    public void accept(List<GPSPoint> data) {

    }

    @Override
    public void accept(Result result, List<GPSPoint> data) {

    }

    @Override
    public Future<Result> predict() {
        return null;
    }
}

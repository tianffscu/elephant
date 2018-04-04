package cn.tianff.elephant.algorithm.clustering;

import org.apache.commons.math3.ml.clustering.*;

public class Clusterers {

    private Clusterers() {
    }

    public static <T extends Clusterable> Clusterer<T> newClustererBy(Class clazz) {
        // TODO: 2018/4/4
        return null;
    }

    // TODO: 2018/3/21
    public static <T extends Clusterable> KMeansPlusPlusClusterer<T> newKeansPlusPlusClusterer() {

        return null;
    }

    // TODO: 2018/3/21
    public static <T extends Clusterable> MultiKMeansPlusPlusClusterer<T> newMultiKMeansPlusPlusClusterer() {

        return null;
    }

    // TODO: 2018/3/21
    public static <T extends Clusterable> DBSCANClusterer<T> newDBSCANClusterer() {

        return null;
    }

    // TODO: 2018/3/21
    public static <T extends Clusterable> FuzzyKMeansClusterer<T> newFuzzyKMeansClusterer() {

        return null;
    }
}

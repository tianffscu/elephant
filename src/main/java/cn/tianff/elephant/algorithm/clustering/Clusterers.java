package cn.tianff.elephant.algorithm.clustering;

import org.apache.commons.math3.ml.clustering.*;

// TODO: 2018/4/6 由于clusterer.cluster方法返回的泛型无法匹配
public class Clusterers {

    private Clusterers() {
    }

    public static <E extends Clusterer, T extends Clusterable> Clusterer newClustererBy(Class<E> clusterer, Class<T> clusterable) {
        // TODO: 2018/4/4
        String clustererName = clusterer.getSimpleName();
        switch (clustererName) {
            case "KMeansPlusPlusClusterer":
                return newKeansPlusPlusClusterer(clusterable);
            case "MultiKMeansPlusPlusClusterer":
                return newMultiKMeansPlusPlusClusterer(clusterable);
            case "DBSCANClusterer":
                return newDBSCANClusterer(clusterable);
            case "FuzzyKMeansClusterer":
                return newFuzzyKMeansClusterer(clusterable);
            default:
                break;
        }

        throw new IllegalArgumentException("No such Algorithm!");
    }

    // TODO: 2018/3/21
    public static <T extends Clusterable> KMeansPlusPlusClusterer<T> newKeansPlusPlusClusterer(Class<T> clazz) {

        return null;
    }

    // TODO: 2018/3/21
    public static <T extends Clusterable> MultiKMeansPlusPlusClusterer<T> newMultiKMeansPlusPlusClusterer(Class<T> clazz) {

        return null;
    }

    // TODO: 2018/3/21
    public static <T extends Clusterable> DBSCANClusterer<T> newDBSCANClusterer(Class<T> clazz) {

        return null;
    }

    // TODO: 2018/3/21
    public static <T extends Clusterable> FuzzyKMeansClusterer<T> newFuzzyKMeansClusterer(Class<T> clazz) {

        return null;
    }
}

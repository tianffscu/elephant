package cn.tianff.elephant.algorithm.predicting;

public class Property {

    private double gridLength;
    private double gridHeight;

    private double clusterRadius;

    private double minPts;

    private int maxNumInCluster;
    private int minNumInCluster;

    private double maxClusterInfectRadius;
    private double minClusterInfectRadius;

    private double similarityDuringModeling;
    private double similarityDuringUpdating;

    private double deleteFromClusterLimit;

    private double minPredictProbability;

    private double clusterMinimize;

    public Property() {
    }

    public double getGridLength() {
        return gridLength;
    }

    public void setGridLength(double gridLength) {
        this.gridLength = gridLength;
    }

    public double getGridHeight() {
        return gridHeight;
    }

    public void setGridHeight(double gridHeight) {
        this.gridHeight = gridHeight;
    }

    public double getClusterRadius() {
        return clusterRadius;
    }

    public void setClusterRadius(double clusterRadius) {
        this.clusterRadius = clusterRadius;
    }

    public double getMinPts() {
        return minPts;
    }

    public void setMinPts(double minPts) {
        this.minPts = minPts;
    }

    public int getMaxNumInCluster() {
        return maxNumInCluster;
    }

    public void setMaxNumInCluster(int maxNumInCluster) {
        this.maxNumInCluster = maxNumInCluster;
    }

    public int getMinNumInCluster() {
        return minNumInCluster;
    }

    public void setMinNumInCluster(int minNumInCluster) {
        this.minNumInCluster = minNumInCluster;
    }

    public double getMaxClusterInfectRadius() {
        return maxClusterInfectRadius;
    }

    public void setMaxClusterInfectRadius(double maxClusterInfectRadius) {
        this.maxClusterInfectRadius = maxClusterInfectRadius;
    }

    public double getMinClusterInfectRadius() {
        return minClusterInfectRadius;
    }

    public void setMinClusterInfectRadius(double minClusterInfectRadius) {
        this.minClusterInfectRadius = minClusterInfectRadius;
    }

    public double getSimilarityDuringModeling() {
        return similarityDuringModeling;
    }

    public void setSimilarityDuringModeling(double similarityDuringModeling) {
        this.similarityDuringModeling = similarityDuringModeling;
    }

    public double getSimilarityDuringUpdating() {
        return similarityDuringUpdating;
    }

    public void setSimilarityDuringUpdating(double similarityDuringUpdating) {
        this.similarityDuringUpdating = similarityDuringUpdating;
    }

    public double getDeleteFromClusterLimit() {
        return deleteFromClusterLimit;
    }

    public void setDeleteFromClusterLimit(double deleteFromClusterLimit) {
        this.deleteFromClusterLimit = deleteFromClusterLimit;
    }

    public double getMinPredictProbability() {
        return minPredictProbability;
    }

    public void setMinPredictProbability(double minPredictProbability) {
        this.minPredictProbability = minPredictProbability;
    }

    public double getClusterMinimize() {
        return clusterMinimize;
    }

    public void setClusterMinimize(double clusterMinimize) {
        this.clusterMinimize = clusterMinimize;
    }
}

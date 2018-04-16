package cn.tianff.elephant.model.location;

import cn.tianff.elephant.model.tracking.TimePeriod;
import org.apache.commons.math3.ml.clustering.Clusterable;

public class GPSGridLocation implements Clusterable {

    /**
     * We split the 2-Dimensional plane as grids,
     * take a grid as one unified pixel,
     * using (grid_x,grid_y) to describe the location of a specific grid.
     */
    private double gridX;
    private double gridY;

    private TimePeriod timePeriod;

    private int contains;

    /**
     * todo: 修改构造函数使得通过构造函数传参可以完成经纬度到grid_x，grid_y的转换
     */
    protected GPSGridLocation() {
    }

    // TODO: 2018/4/14
    public GPSGridLocation(GPSPoint point, TimePeriod timePeriod) {

    }

    public GPSGridLocation(double gridX, double gridY) {
        this.gridX = gridX;
        this.gridY = gridY;
    }


    public GPSGridLocation(double gridX, double gridY, TimePeriod time, int contains) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.timePeriod = time;
        this.contains = contains;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    /**
     * if a GPS point in the specific zone
     *
     * @param point GPS point with lon. & lat.
     * @return true-> point in zone
     */
    public boolean contains(GPSPoint point) {
        // TODO: 2018/3/22
        return false;
    }

    public double getGridX() {
        return gridX;
    }

    public double getGridY() {
        return gridY;
    }

    public int getContains() {
        return contains;
    }

    public void setContains(int contains) {
        this.contains = contains;
    }

    @Override
    public double[] getPoint() {
        return new double[]{gridX, gridY};
    }
}

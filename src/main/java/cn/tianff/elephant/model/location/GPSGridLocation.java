package cn.tianff.elephant.model.location;

import cn.tianff.elephant.model.tracking.TimePeriod;
import org.apache.commons.math3.ml.clustering.Clusterable;

public class GPSGridLocation implements Clusterable{

    /**
     * We split the 2-Dimensional plane as grids,
     * take a grid as one unified pixel,
     * using (grid_x,grid_y) to describe the location of a specific grid.
     */
    private double gridX;
    private double gridY;

    private TimePeriod timePeriod;

    /**
     * todo: 修改构造函数使得通过构造函数传参可以完成经纬度到grid_x，grid_y的转换
     */
    protected GPSGridLocation() {
    }

    public GPSGridLocation(GPSPoint point, TimePeriod timePeriod) {

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

    // TODO: 2018/4/4  
    @Override
    public double[] getPoint() {
        return new double[0];
    }
}

package cn.tianff.elephant.model.location;

public class GPSGridLocation {

    /**
     * We split the 2-Dimensional plane as grids,
     * take a grid as one unified pixel,
     * using (grid_x,grid_y) to describe the location of a specific grid.
     */
    private double gridX;
    private double gridY;

    /**
     * todo: 修改构造函数使得通过构造函数传参可以完成经纬度到grid_x，grid_y的转换
     */
    public GPSGridLocation() {
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
}

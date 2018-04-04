package cn.tianff.elephant.model.location;

public class GPSGridLocation {

    /**
     * Six period of one day
     */
    public static final int Midnight2SixAM = 0;
    public static final int SixAM2NineAM = 1;
    public static final int NineAM2Noon = 2;
    public static final int Noon2TwoPM = 3;
    public static final int TwoPM2SixPM = 4;
    public static final int SixPM2MidNight = 5;

    /**
     * We split the 2-Dimensional plane as grids,
     * take a grid as one unified pixel,
     * using (grid_x,grid_y) to describe the location of a specific grid.
     */
    private double gridX;
    private double gridY;

    private int timePeriod;

    /**
     * todo: 修改构造函数使得通过构造函数传参可以完成经纬度到grid_x，grid_y的转换
     */
    protected GPSGridLocation() {
    }

    public GPSGridLocation(GPSPoint point, int timePeriod) {

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
}

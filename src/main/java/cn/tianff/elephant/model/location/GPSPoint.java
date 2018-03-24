package cn.tianff.elephant.model.location;

public class GPSPoint {

    private double longitude;
    private double latitude;

    public GPSPoint() {
    }

    public GPSPoint(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}



package cn.tianff.elephant.clustering;

import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Testcase for clustering algorithms
 */
public class BaseAlgorithmTest {

    private double[] xList = new double[]{30, 31, 33, 24, 36, 29, 110, 109, 111, 112, 113, 108, 104, 70, 71, 66, 69, 68, 67, 72, 73};
    private double[] yList = new double[]{20, 22, 35, 21, 24, 20, 230, 240, 229, 228, 234, 235, 231, 145, 146, 147, 149, 148, 145, 140, 150};


    @Test
    public void testDBSCAN() {
        List<Location> packet = generatePoints();
        DBSCANClusterer<Location> clusterDBSCAN = new DBSCANClusterer<>(15, 1);
        List<Cluster<Location>> result = clusterDBSCAN.cluster(packet);

        System.out.println(result.size() + " clusters were found:\n\n");
        result.forEach(this::clusterPrinter);
    }

    private void clusterPrinter(Cluster<Location> cluster) {
        System.out.println("**************Print Points For Cluster***************");
        List<Location> points = cluster.getPoints();
        points.forEach(System.out::println);
        System.out.println("\n");
    }

    private List<Location> generatePoints() {
        //Use hash to get a random point
        HashSet<Location> packet = new HashSet<>();
        for (int i = 0; i < xList.length; i++) {
            Location point = new Location(xList[i], yList[i]);
            packet.add(point);
        }
        return new ArrayList<>(packet);
    }


    class Location implements Clusterable {

        double locationX;
        double locationY;

        public Location() {
        }

        public Location(double locationX, double locationY) {
            this.locationX = locationX;
            this.locationY = locationY;
        }

        @Override
        public double[] getPoint() {
            return new double[]{locationX, locationY};
        }

        @Override
        public String toString() {
            return "Location{" +
                    "locationX=" + locationX +
                    ", locationY=" + locationY +
                    '}';
        }
    }
}

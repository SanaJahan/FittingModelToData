import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import model.Centroid;
import model.DataPoint;
import model.EuclideanDistance;
import model.IDistance;
import model.IKMeansClusteringModel;
import model.KMeansClusteringModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tester class for the KMeans clustering algorithm implementation.
 */
public class TestKMeansClusteringModel {
  private IKMeansClusteringModel kMeansClusteringModel;
  private ArrayList<DataPoint> dataPoints = new ArrayList<>();

  @Before
  public void setUp() {
    kMeansClusteringModel = new KMeansClusteringModelImpl();
    dataPoints.add(new DataPoint(2, 3));
    dataPoints.add(new DataPoint(3, 4));
    dataPoints.add(new DataPoint(4, 5));
    dataPoints.add(new DataPoint(2.5, 3));
    dataPoints.add(new DataPoint(2.5, 3.5));
    dataPoints.add(new DataPoint(5, 4.5));
    dataPoints.add(new DataPoint(6, 5));
    dataPoints.add(new DataPoint(7, 5.5));
    dataPoints.add(new DataPoint(8, 6));
  }

  /**
   *********************************** TEST FOR CREATING THE K CLUSTERS **************************.
   */
  /**
   * test for k = 0.
   */
  @Test
  public void shouldPassForKZero() {
    ArrayList<Centroid> centroids =
            kMeansClusteringModel.createClusters(dataPoints, 0);
    assertEquals(0, centroids.size());
  }

  /**
   * test for data points empty with non-zero k.
   */
  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowErrorForEmptyDataPoints() {
    dataPoints.removeAll(dataPoints);
    ArrayList<Centroid> centroids =
            kMeansClusteringModel.createClusters(dataPoints, 0);
  }

  /**
   * test with one data entry and k greater than data entry size.
   */
  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowErrorForKGreaterThanDataPointSize() {
    ArrayList<Centroid> centroids =
            kMeansClusteringModel.createClusters(dataPoints, dataPoints.size() + 1);
  }

  /**
   * test with one data entry and k = size of data entry.
   */
  @Test
  public void shouldReturnSameDataEntryAsCentroid() {
    dataPoints.clear();
    dataPoints.add(new DataPoint(4, 5));
    ArrayList<Centroid> centroids =
            kMeansClusteringModel.createClusters(dataPoints, 1);
    assertEquals(dataPoints.get(0).getXCoordinate(), centroids.get(0).getxCoordinate(), 6);
  }

  /**
   * test with many data entries and an appropriate k.
   */
  @Test
  public void shouldCreateKClusters() {
    ArrayList<Centroid> centroids = kMeansClusteringModel.createClusters(dataPoints, 3);
    assertEquals(3, centroids.size());
  }

  /**
   ************************  TEST FOR GETTING THE NEAREST NEIGHBORS FOR GIVEN DATA SETS  *********.
   */
  /**
   * test when cluster is zero this situation will never occur since the createCluster will throw,
   * error if no cluster will get created.
   */

  @Test
  public void shouldWorkForEmptyOrNullCluster() {
    ArrayList<Centroid> clusters = new ArrayList<>();
    DataPoint dataPoint = new DataPoint(3.4, 4.5);
    IDistance distance = new EuclideanDistance();
    Centroid centroid = kMeansClusteringModel.nearestCentroid(dataPoint, clusters, distance);
    assertNull(centroid);
  }

  /**
   * test when one data point is there and centroid is not null.
   */
  @Test
  public void shouldThrowErrorIfDataPointNotValid() {
    ArrayList<Centroid> clusters = new ArrayList<>();
    clusters.add(new Centroid(2, 3));
    DataPoint dataPoint = new DataPoint(0, 0);
    IDistance distance = new EuclideanDistance();
    Centroid centroid = kMeansClusteringModel.nearestCentroid(dataPoint, clusters, distance);
    assertEquals(2.0, centroid.getxCoordinate(), 1);
  }

  /**
   * test for one data point and two centroids.
   */
  @Test
  public void shouldGiveTheNearestCentroid() {
    ArrayList<Centroid> clusters = new ArrayList<>();
    //nearest centroid
    clusters.add(new Centroid(2, 3));
    //farther centroid
    clusters.add(new Centroid(4, 3));
    DataPoint dataPoint = new DataPoint(0, 0);
    IDistance distance = new EuclideanDistance();
    Centroid centroid = kMeansClusteringModel.nearestCentroid(dataPoint, clusters, distance);

    assertEquals(2.0, centroid.getxCoordinate(), 1);
  }

  /**
   * more than one data sets and more than one centroid.
   */
  @Test
  public void shouldGiveCorrectClusterForAllDataSets() {
    ArrayList<Centroid> clusters = new ArrayList<>();
    clusters.add(new Centroid(2, 3));
    clusters.add(new Centroid(4, 3));
    DataPoint dataPoint = new DataPoint(2, 4);
    DataPoint dataPoint2 = new DataPoint(5, 3);
    DataPoint dataPoint3 = new DataPoint(1, 3);
    IDistance distance = new EuclideanDistance();
    Centroid centroid = kMeansClusteringModel.nearestCentroid(dataPoint, clusters, distance);
    Centroid centroid1 = kMeansClusteringModel.nearestCentroid(dataPoint2, clusters, distance);
    Centroid centroid2 = kMeansClusteringModel.nearestCentroid(dataPoint3, clusters, distance);

    assertEquals(2.0, centroid.getxCoordinate(), 1);
    assertEquals(4.0, centroid1.getxCoordinate(), 1);
    assertEquals(2.0, centroid2.getxCoordinate(), 1);
  }

  /**
   * TEST THE RELOCATION OF EACH CENTROID OF THE CLUSTER.
   */
  /**
   * the centroid has no datasets.
   */
  @Test
  public void shouldReturnTheCentroidWithoutRelocating() {
    dataPoints.clear();
    Centroid centroid = new Centroid(3, 4);
    kMeansClusteringModel.relocate(centroid);
    assertEquals(3.0, centroid.getxCoordinate(), 6);
    assertEquals(4.0, centroid.getyCoordinate(), 6);
    assertNull(centroid.getDataPoints());
  }

  /**
   * the centroid has one data point only.
   */
  @Test
  public void shouldReturnCentroidWithXYSameAsDataSet() {
    dataPoints.clear();
    dataPoints.add(new DataPoint(3.5,4.5));
    ArrayList<Centroid> centroids = kMeansClusteringModel.createClusters(dataPoints, 1);
    kMeansClusteringModel.nearestCentroid(new DataPoint(3.5, 4.5),
            centroids, new EuclideanDistance());

    kMeansClusteringModel.assignToCluster(centroids, new DataPoint(3.5, 4.5), centroids.get(0));
    kMeansClusteringModel.relocate( centroids.get(0));

    assertEquals(3.5, centroids.get(0).getxCoordinate(), 6);
    assertEquals(4.5, centroids.get(0).getyCoordinate(), 6);
  }

  /**
   * the centroid has more than one data set.
   */
  @Test
  public void tetRelocationOfCluster() {
    ArrayList<Centroid> centroids = kMeansClusteringModel.createClusters(dataPoints, 3);
    kMeansClusteringModel.nearestCentroid(new DataPoint(3.5, 4.5),
            centroids, new EuclideanDistance());

    kMeansClusteringModel.assignToCluster(centroids, new DataPoint(3.5, 4.5), centroids.get(0));
    kMeansClusteringModel.relocate( centroids.get(0));
    assertEquals(3.5, centroids.get(0).getxCoordinate(), 1);
  }

}

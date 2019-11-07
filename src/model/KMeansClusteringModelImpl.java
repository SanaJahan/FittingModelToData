package model;

import java.util.ArrayList;
import java.util.Random;

import utility.MeanHelper;

public class KMeansClusteringModelImpl implements IKMeansClusteringModel {

  private static final Random random = new Random();
  private double error = Double.POSITIVE_INFINITY;
  private double ne = 0;
  private double percentageError = 0;
  private ArrayList<Centroid> finalCentroids = new ArrayList<>();


  /**
   * method to create the cluster from the randomly chosen datapoints.
   * each entry in the list is a cluster.
   * k is the number of clusters we want.
   *
   * @return the randomly chosen k points for the cluster
   */
  @Override
  public ArrayList<Centroid> createClusters(ArrayList<DataPoint> dataSets, int k) throws IllegalArgumentException {
    if (dataSets == null || dataSets.size() < 1) {
      throw new IllegalArgumentException("no data sets found");
    }
    if( k <= dataSets.size()) {
      ArrayList<Centroid> clusters = new ArrayList<>();
      for (int i = 0; i < k; i++) {
        Centroid centroid = createCentroid(dataSets);
        // in this case, cluster is less than k
        if (!clusters.contains(centroid)) {
          clusters.add(centroid);
        }
      }
      return clusters;
    } else {
      throw new IllegalArgumentException("k cannot be greater than the size of the data points");
    }
  }


  /**
   * Computes the nearest Centroid based on the euclidean distance between the data point,
   * coordinates and the cluster points.
   * @param dataPoint DataPoint with coordinates
   * @param clusters Centroid list with cluster points.
   * @param distance Euclidean Distance.
   * @return The nearest cluster based on the computation.
   */
  @Override
  public Centroid nearestCentroid(DataPoint dataPoint, ArrayList<Centroid> clusters, IDistance distance) {
    double minimumDistance = Double.MAX_VALUE;
    Centroid nearest = null;
    for (Centroid cluster : clusters) {
      double currentDistance = distance.calculate(dataPoint, cluster);
      if (currentDistance < minimumDistance) {
        minimumDistance = currentDistance;
        nearest = cluster;
      }
    }

    return nearest;
  }


  /**
   * Method for assigning the centroids to the respective clusters.
   * @param clusters List of Centroid type clusters.
   * @param dataPoint DataPoint with coordinates.
   * @param centroid Centroid to assign.
   */
  @Override
  public void assignToCluster(ArrayList<Centroid> clusters, DataPoint dataPoint, Centroid centroid) {
    // if the centroid not part of the cluster simply return
    if (!clusters.contains(centroid)) {
      return;
    }
      // if the list of the centroid is null, create a new list and then add it
      if (centroid.getDataPoints() == null) {
        ArrayList<DataPoint> d = new ArrayList<>();
        d.add(dataPoint);
        centroid.setDataPoints(d);
        return;
      }
      // it does not contain the dataPoint already
      // add the dataPoint to its existing list
      if (!centroid.getDataPoints().contains(dataPoint)) {
        centroid.getDataPoints().add(dataPoint);
        return;
      }
      // if it already contains return
      return;
  }


  /**
   * For relocating the centroid.
   * @param centroid Centroid to be relocated.
   */
  @Override
  public void relocate(Centroid centroid) {
    MeanHelper meanHelper = new MeanHelper();
    // if there is not dataset in the centroid list, return the centroid as it is
    if (centroid.getDataPoints() == null || centroid.getDataPoints().isEmpty()) {
      return;
    }
    // if it has a list, find the mean of the coordinates and set the centroid's x and y to that value
    double meanX = meanHelper.meanOfXCoordinates(centroid.getDataPoints());
    double meanY = meanHelper.meanOfYCoordinates(centroid.getDataPoints());
    centroid.setxCoordinate(meanX);
    centroid.setyCoordinate(meanY);
  }


  /**
   * Relocates all the clusters.
   * @param clusters List of Centroid type clusters.
   * @return The Centroid list of clusters.
   */
  @Override
  public ArrayList<Centroid> relocateCentroids(ArrayList<Centroid> clusters) {
    for (Centroid c : clusters) {
      relocate(c);
    }
    return clusters;
  }


  /**
   * Improves the final result by refitting.
   * @param dataPoints List of data points.
   * @param k Number of clusters.
   * @param IDistance Euclidean Distance.
   * @param maxIterations The maximum number of iterations allowed.
   * @return The final list of Centroid clusters.
   */
  @Override
  public ArrayList<Centroid> fit(ArrayList<DataPoint> dataPoints, int k, IDistance IDistance, int maxIterations) {
    ArrayList<Centroid> clusters = createClusters(dataPoints, k);
    ArrayList<Centroid> lastState = new ArrayList<>();

    // iterate for a pre-defined number of times
    for (int i = 0; i < maxIterations; i++) {
      boolean isLastIteration = i == maxIterations - 1;
      // in each iteration we should find the nearest centroid for each dataPoint
      for (DataPoint dataPoint : dataPoints) {
        Centroid centroid = nearestCentroid(dataPoint, clusters, IDistance);
        assignToCluster(clusters, dataPoint, centroid);
      }

      // if the assignments do not change, then the algorithm terminates
      boolean shouldTerminate = isLastIteration || clusters.equals(lastState);
      lastState = clusters;
      if (shouldTerminate) {
        break;
      }
      // percentage error threshold
      if (percentageError == 0.01) {
        break;
      }

      // at the end of each iteration we should relocate the centroids
      clusters = relocateCentroids(clusters);
      // calculate average  for each dataPoint in that centroid, and assign to ne
      ne = newError(clusters, IDistance);
    }

    return lastState;
  }


  //method to compute ne

  /**
   * Computes the ne calculation to be used in error computation.
   * @param centroids List of Centroid clusters.
   * @param IDistance Euclidean Distance.
   * @return New error.
   */
  @Override
  public double newError(ArrayList<Centroid> centroids, IDistance IDistance) {
    double error = 0;
    double avgOf = 1;
    for (Centroid centroid : centroids) {
      if (centroid.getDataPoints() != null) {
        for (DataPoint dataPoint : centroid.getDataPoints()) {
          double currentDistance = IDistance.calculate(dataPoint, centroid);
          error += currentDistance;
        }
        avgOf += centroid.getDataPoints().size();
      }
    }
    return error / avgOf;
  }


  /**
   * Original fitting computation.
   * @param dataPoints List of data points with coordinates.
   * @param k Number of clusters.
   * @param IDistance Euclidean Distance.
   * @param max The maximum number of iterations allowed.
   * @return The list of Centroid clusters.
   */
  @Override
  public ArrayList<Centroid> bestFit(ArrayList<DataPoint> dataPoints, int k, IDistance IDistance, int max) {
    for (int i = 0; i < 10; i++) {
      ArrayList<Centroid> centroids = fit(dataPoints, k, new EuclideanDistance(), max);
      if (ne < error) {
        error = ne;
        // calculate percentage error
        percentageError = Math.abs(ne - error) / error;
        error = ne;
        finalCentroids = centroids;
      }
    }

    return finalCentroids;
  }

  /**
   * Returns a random number based on the datasets.
   * @param dataSets List of data points.
   * @return Random integer.
   */
  private int generateRandom(ArrayList<DataPoint> dataSets) {
    if(dataSets.size() == 1) {
      return 0;
    }
    int rand = random.nextInt(dataSets.size() - 1);
    return rand > 0 ? rand : generateRandom(dataSets);
  }

  /**
   * Creates the centroid.
   * @param dataSets list of data points.
   * @return Centroid object.
   */
  private Centroid createCentroid(ArrayList<DataPoint> dataSets) {
    DataPoint d = dataSets.get(generateRandom(dataSets));
    double xCoordinate = d.getXCoordinate();
    double yCoordinate = d.getYCoordinate();
    Centroid centroid = new Centroid(xCoordinate, yCoordinate);
    return centroid;
  }
}

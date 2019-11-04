package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import controller.DataController;
import utility.MeanHelper;

public class KMeansClusteringModelImpl implements IKMeansClusteringModel {

  private static final Random random = new Random();
  private double error = Double.POSITIVE_INFINITY;
  private double ne = 0;
  private double percentageError = 0;

  @Override
  public ArrayList<Centroid> createClusters(int k) throws IOException {
    DataController dataController = new DataController();
    ArrayList<DataPoint> dataSets = dataController.readClusterDataSet();
    ArrayList<Centroid> clusters = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      DataPoint d = dataSets.get(random.nextInt(dataSets.size()-1));
      double xCoordinate = d.getXCoordinate();
      double yCoordinate = d.getYCoordinate();
      Centroid centroid = new Centroid(xCoordinate, yCoordinate);
      if (!clusters.contains(centroid)) {
        clusters.add(centroid);
      }
    }
    return clusters;
  }


  @Override
  public Centroid nearestCentroid(DataPoint dataPoint, ArrayList<Centroid> clusters, Distance distance) throws IOException {
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


  @Override
  public void assignToCluster(ArrayList<Centroid> clusters, DataPoint dataPoint, Centroid centroid) {
    for (Centroid c : clusters) {
      // if the centroid not part of the cluster simply return
      if (!clusters.contains(centroid)) {
        return;
      }
      if (clusters.contains(centroid)) {
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
    }
  }


  @Override
  public Centroid relocate(Centroid centroid) throws IOException {
    MeanHelper meanHelper = new MeanHelper();
    // if there is not dataset in the centroid list, return the centroid as it is
    if (centroid.getDataPoints() == null || centroid.getDataPoints().isEmpty()) {
      return centroid;
    }
    // if it has a list, find the mean of the coordinates and set the centroid's x and y to that value
    double meanX = meanHelper.meanOfXCoordinates(centroid.getDataPoints());
    double meanY = meanHelper.meanOfYCoordinates(centroid.getDataPoints());
    centroid.setxCoordinate(meanX);
    centroid.setyCoordinate(meanY);
    return centroid;
  }


  // relocates all the clusters
  @Override
  public ArrayList<Centroid> relocateCentroids(ArrayList<Centroid> clusters) throws IOException {
    for (Centroid c : clusters) {
      relocate(c);
    }
    return clusters;
  }

  // method to improve the result
  @Override
  public ArrayList<Centroid> fit(ArrayList<DataPoint> dataPoints, int k, Distance distance, int maxIterations) throws IOException {
    ArrayList<Centroid> clusters = createClusters(k);
    ArrayList<Centroid> lastState = new ArrayList<>();


    // iterate for a pre-defined number of times
    for (int i = 0; i < maxIterations; i++) {
      boolean isLastIteration = i == maxIterations - 1;
      // in each iteration we should find the nearest centroid for each dataPoint
      for (DataPoint dataPoint : dataPoints) {
        Centroid centroid = nearestCentroid(dataPoint, clusters, distance);
        assignToCluster(clusters, dataPoint, centroid);
      }

      // if the assignments do not change, then the algorithm terminates
      boolean shouldTerminate = isLastIteration || clusters.equals(lastState);
      lastState = clusters;
      if (shouldTerminate) {
        break;
      }

      // at the end of each iteration we should relocate the centroids
      clusters = relocateCentroids(clusters);
      // calculate average  for each dataPoint in that centroid, and assign to ne
      ne = newError(clusters,distance);
      if( i == 0 ){
        error = ne;
      }
      // calculate percentage error
      percentageError = Math.abs(ne - error)/error;
      error = ne;
      // percentage error threshold
      if(percentageError == 0.01) {
        break;
      }
      error = ne;
    }

    return lastState;
  }


  //method to compute ne
  public double newError(ArrayList<Centroid> centroids, Distance distance) throws IOException {
    double error = 0;
    double avgOf = 1;
    for (Centroid centroid : centroids) {
      if (centroid.getDataPoints() != null) {
        for (DataPoint dataPoint : centroid.getDataPoints()) {
          double currentDistance = distance.calculate(dataPoint, centroid);
          error += currentDistance;
        }
        avgOf += centroid.getDataPoints().size();
      }
    }
      return error / avgOf;
    }
  //method to calculate error percentage


}

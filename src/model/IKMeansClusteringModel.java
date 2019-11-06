package model;

import java.io.IOException;
import java.util.ArrayList;

public interface IKMeansClusteringModel {

  /**
   * method to create the cluster from the randomly chosen datapoints.
   * each entry in the list is a cluster.
   * k is the number of clusters we want.
   *
   * @return the randomly chosen k points for the cluster
   */
  ArrayList<Centroid> createClusters(ArrayList<DataPoint> dataSets, int k);
  Centroid nearestCentroid(DataPoint dataPoint, ArrayList<Centroid> clusters, IDistance IDistance) throws IOException;

  void assignToCluster(ArrayList<Centroid> clusters, DataPoint dataPoint, Centroid centroid);

  Centroid relocate(Centroid centroid) throws IOException;

  ArrayList<Centroid> relocateCentroids(ArrayList<Centroid> clusters) throws IOException;

  ArrayList<Centroid> fit(ArrayList<DataPoint> dataPoints, int k, IDistance IDistance, int maxIterations) throws IOException;

  double newError(ArrayList<Centroid> centroids, IDistance IDistance) throws IOException;

  ArrayList<Centroid> bestFit(ArrayList<DataPoint> dataPoints, int k, IDistance IDistance, int max) throws IOException;

}

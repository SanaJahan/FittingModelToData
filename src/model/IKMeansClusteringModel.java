package model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface for the class representing the KMeansClusteringModelImpl.
 */
public interface IKMeansClusteringModel {


  ArrayList<Centroid> createClusters(ArrayList<DataPoint> dataSets, int k);

  Centroid nearestCentroid(DataPoint dataPoint, ArrayList<Centroid> clusters, IDistance distance);

  void assignToCluster(ArrayList<Centroid> clusters, DataPoint dataPoint, Centroid centroid);

  void relocate(Centroid centroid);

  ArrayList<Centroid> relocateCentroids(ArrayList<Centroid> clusters) throws IOException;

  ArrayList<Centroid> fit(ArrayList<DataPoint> dataPoints, int k, IDistance distance,
                          int maxIterations);

  double newError(ArrayList<Centroid> centroids, IDistance distance) throws IOException;

  ArrayList<Centroid> bestFit(ArrayList<DataPoint> dataPoints, int k, IDistance distance,
                              int max);

}

package mvc;

import java.util.List;

public interface FittingAlgorithm {

  double meanOfXCoordinates(List<Double> xCoordinates, int totalX);// needed in k-means hence goes to abstract class

  double meanOfYCoordinates(List<Double> yCoordinates, int totalX);// needed in k-means hence goes to abstract class

  double squareOfXCoordinates(double meanOfXCoordinates);

  double squareOfYCoordinates(double meanOfYCoordinates);

  double squareOfXYCoordinates(double meanOfXYCoordinates);

  double computeD();

  double computeTheta();

  double computeModel(int t) throws IllegalArgumentException;// throws exception if f(t) is negative due to t

  double computeCos(int t);

  double computeSin(int t);

  double computeC();

  // for K means
  //method to choose k points from the dataset at random.gives the center points of k clusters
  //method to categorise the point based on distance of it from the center of the cluster
  // hence we need a method to find the center of the cluster
  //method to compute ne
  //method to calculate error percentage
  //method to report the cluster assigned to each data point
  // method to improve the result

}

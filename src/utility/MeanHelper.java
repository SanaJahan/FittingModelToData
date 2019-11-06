package utility;

import java.util.ArrayList;

import model.DataPoint;

/**
 * This class holds the common mean calculation methods for both the algorithmic implementations.
 */
public class MeanHelper  {

  /**
   * Computes the mean of the X coordinates of all the DataPoints.
   * @param xCoordinates The list of DataPoints.
   * @return The final mean.
   */
  public double meanOfXCoordinates(ArrayList<DataPoint> xCoordinates) {
    double sum = 0;
    for (DataPoint d: xCoordinates) {
      sum += d.getXCoordinate();
    }
    int totalX = xCoordinates.size();
    return sum/totalX;
  }// needed in k-means hence goes to abstract class

  /**
   * Computes the mean of the Y coordinates of all the DataPoints.
   * @param yCoordinates The list of DataPoints.
   * @return The final mean.
   */
  public double meanOfYCoordinates(ArrayList<DataPoint> yCoordinates) {
    double sum = 0;
    for (DataPoint d: yCoordinates) {
      sum += d.getYCoordinate();
    }
    int totalY = yCoordinates.size();
     return sum/totalY;
  }// needed in k-means hence goes to abstract class

}

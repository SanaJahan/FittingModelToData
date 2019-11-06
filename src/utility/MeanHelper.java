package utility;

import java.io.IOException;
import java.util.ArrayList;

import controller.DataController;
import model.DataPoint;

/**
 * This class holds the common mean calculation methods for both the algorithmic implementations.
 */
public class MeanHelper  {
  private DataController dc;

  /**
   * Computes the mean of the X coordinates of all the DataPoints.
   * @param xCoordinates The list of DataPoints.
   * @return The final mean.
   * @throws IOException Thrown at IOException.
   */
  public double meanOfXCoordinates(ArrayList<DataPoint> xCoordinates) {
    double sum = 0;
    for (DataPoint d: xCoordinates) {
      sum += d.getXCoordinate();
    }
    int totalX = dc.getDataSize();
    double mean = sum/totalX;
    return mean;
  }// needed in k-means hence goes to abstract class

  /**
   * Computes the mean of the Y coordinates of all the DataPoints.
   * @param yCoordinates The list of DataPoints.
   * @return The final mean.
   * @throws IOException Thrown at IOException.
   */
  public double meanOfYCoordinates(ArrayList<DataPoint> yCoordinates) {
    double sum = 0;
    for (DataPoint d: yCoordinates) {
      sum += d.getYCoordinate();
    }
    int totalY = dc.getDataSize();
  double mean = sum/totalY;
    return mean;
  }// needed in k-means hence goes to abstract class

}

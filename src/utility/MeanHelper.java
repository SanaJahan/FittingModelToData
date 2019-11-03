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

  double meanOfXCoordinates(ArrayList<DataPoint> xCoordinates) throws IOException {
    double sum = 0;
    dc = new DataController();
    for (DataPoint d: xCoordinates) {
      sum += d.getXCoordinate();
    }
    int totalX = dc.readLineDataSet().size();
    double mean = sum/totalX;
    return mean;
  }// needed in k-means hence goes to abstract class

  double meanOfYCoordinates(ArrayList<DataPoint> yCoordinates) throws IOException {
    double sum = 0;
    dc = new DataController();
    for (DataPoint d: yCoordinates) {
      sum += d.getYCoordinate();
    }
    int totalY = dc.readLineDataSet().size();
    double mean = sum/totalY;
    return mean;
  }// needed in k-means hence goes to abstract class





}
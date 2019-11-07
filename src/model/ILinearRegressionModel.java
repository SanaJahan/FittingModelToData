package model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface for implementation of the LinearRegressionModelImpl class for fitting the best line.
 */
public interface ILinearRegressionModel {


  double sumOfXSquares(ArrayList<DataPoint> lineCoordinates) throws IOException;

  double sumOfYSquares(ArrayList<DataPoint> lineCoordinates) throws IOException;

  double sumOfXYSquares(ArrayList<DataPoint> lineCoordinates) throws IOException;

  double computeDTheta() throws IOException;

  void computeFunctionOfT() throws IllegalArgumentException,IOException;

  double computeYCoordinate() throws IOException;

  double computeXCoordinate() throws IOException;

  double computeC() throws IOException;


}

package model;

import java.io.IOException;
import java.util.ArrayList;

public interface ILinearRegressionModel {


  double sumOfXSquares(ArrayList<DataPoint> lineCoordinates) throws IOException;

  double sumOfYSquares(ArrayList<DataPoint> lineCoordinates) throws IOException;

  double sumOfXYSquares(ArrayList<DataPoint> lineCoordinates) throws IOException;

  double computeDTheta() throws IOException;

  double computeFunctionOfT() throws IllegalArgumentException,IOException;
  double computeYCoordinate() throws IOException;
  double computeXCoordinate() throws IOException;
  public double computeC() throws IOException;


}

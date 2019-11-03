package model;

import java.io.IOException;
import java.util.ArrayList;

public interface ILinearRegressionModel {

  double leastSquares() throws IOException;

  double sumOfXSquares(ArrayList<DataPoint> lineCoordinates) throws IOException;

  double sumOfYSquares(ArrayList<DataPoint> lineCoordinates) throws IOException;

  double sumOfXYSquares(ArrayList<DataPoint> lineCoordinates) throws IOException;

  double computeDTheta() throws IOException;

  boolean computeFunctionOfT() throws IllegalArgumentException,IOException;

  double computeModel() throws IOException;

}

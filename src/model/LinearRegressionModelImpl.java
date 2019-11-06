package model;

import java.io.IOException;
import java.util.ArrayList;

import controller.DataController;
import utility.MeanHelper;

/**
 * Class contains the methods required to compute the least squares line fitting technique to,
 * get the best fit of the line.
 */
public class LinearRegressionModelImpl implements ILinearRegressionModel{
  private MeanHelper meanHelper = new MeanHelper();
  private DataController dataController = new DataController();
  private final ArrayList<DataPoint> dataPoints = dataController.readLineDataSet();

  private static double t = 0;

  public LinearRegressionModelImpl() throws IOException {
  }


  /**
   * Computes the summation of the square of the Y coordinate of each DataPoint object subtracted,
   * from the the mean of all the Y coordinates.
   * @param lineCoordinates The list of DataPoints.
   * @return The total summation.
   * @throws IOException Thrown at IOException.
   */
  public double sumOfYSquares(ArrayList<DataPoint> lineCoordinates) throws IOException {
    double meanY = meanHelper.meanOfYCoordinates(dataPoints);
    double sum = 0;
    for (DataPoint d:lineCoordinates) {
      sum += (d.getYCoordinate() - meanY) * (d.getYCoordinate() - meanY);
    }
    return sum;
  }

  /**
   * Computes the summation of the square of the X coordinate of each DataPoint object subtracted,
   * from the mean of all the X coordinates.
   * @param lineCoordinates The list of DataPoints.
   * @return The total summation.
   * @throws IOException Thrown at IOException.
   */
  public double sumOfXSquares(ArrayList<DataPoint> lineCoordinates) throws IOException {
    double meanX = meanHelper.meanOfXCoordinates(dataPoints);
    double sum = 0;
    for (DataPoint d:lineCoordinates) {
      sum += (d.getXCoordinate() - meanX) * (d.getXCoordinate() - meanX);
    }
    return sum;
  }

  /**
   * Computes the summation of the product of the X coordinate subtracted from the mean of all the,
   * X coordinates and the Y coordinate subtracted from the mean of all the Y coordinates.
   * @param lineCoordinates The list of DataPoints.
   * @return The total summation.
   * @throws IOException Thrown at IOException.
   */
  public double sumOfXYSquares(ArrayList<DataPoint> lineCoordinates) throws IOException {
    double meanX = meanHelper.meanOfXCoordinates(dataPoints);
    double meanY = meanHelper.meanOfYCoordinates(dataPoints);
    double sum = 0;
    for (DataPoint d:lineCoordinates) {
      sum += (d.getXCoordinate() - meanX) * (d.getYCoordinate() - meanY);
    }
    return sum;
  }

  /**
   * Computes the tan inverse value of a formula computation that is required,
   * to perform the least squares operation.
   * @return Tan inverse value.
   * @throws IOException Thrown at the IOException.
   */
  public double computeDTheta() throws IOException{
    dataController = new DataController();
    ArrayList<DataPoint> dc = dataController.readLineDataSet();
    double d = (2 * sumOfXYSquares(dc)) / (sumOfXSquares(dc) - sumOfYSquares(dc));
    return Math.atan(Math.toRadians(d));
  }

  /**
   * Computes a derivation formula for theta and for theta - 180 and it will return the result,
   * depending on whichever function renders positive.
   * @return The derivative function result.
   * @throws IllegalArgumentException Thrown when the result does not match the requirements.
   * @throws IOException Thrown at IOException.
   */
  public double computeFunctionOfT() throws IllegalArgumentException,IOException {
    double functionT = ((sumOfYSquares(dataPoints) - sumOfXSquares(dataPoints)) * Math.cos(Math.toRadians(computeDTheta())))
            - 2 * sumOfXYSquares(dataPoints) * Math.sin(Math.toRadians(computeDTheta()));
    double functionT180 = ((sumOfYSquares(dataPoints) - sumOfXSquares(dataPoints)) * Math.cos(Math.toRadians(computeDTheta() + 180)))
            - 2 * sumOfXYSquares(dataPoints) * Math.sin(Math.toRadians(computeDTheta() + 180));
    if (functionT > 0) {
      t = computeDTheta();
      return functionT;
    }
    else{
      t = computeDTheta()+180;
      return functionT180;
    }
  }

  /**
   * Computes the final model calculation of the best fitting line.
   * @return Final computed value for the best fitting line.
   * @throws IOException Thrown at IOException.
   */
  public double computeXCoordinate() throws IOException {
    computeFunctionOfT();
    double a = Math.cos(Math.toRadians(t/2));
    return -a;
  }

  public double computeYCoordinate() {
    double b = Math.sin(Math.toRadians(t/2));
    return -b;
  }

  public double computeC() throws IOException {
    double c = computeXCoordinate() + computeYCoordinate();
    return c;
  }



}

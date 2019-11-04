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
  private MeanHelper meanHelper;
  private DataController dataController;

  /**
   * Computes the least squares best fitting technique on the line.
   * @return The best fit line.
   * @throws IOException Thrown at IOException.
   */
  public double leastSquares() throws IOException {
    System.out.println(computeC());
    return computeC();
  }

  /**
   * Computes the summation of the square of the Y coordinate of each DataPoint object subtracted,
   * from the the mean of all the Y coordinates.
   * @param lineCoordinates The list of DataPoints.
   * @return The total summation.
   * @throws IOException Thrown at IOException.
   */
  public double sumOfYSquares(ArrayList<DataPoint> lineCoordinates) throws IOException {
    dataController = new DataController();
    meanHelper = new MeanHelper();
    double meanY = meanHelper.meanOfYCoordinates(dataController.readLineDataSet());
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
    dataController = new DataController();
    meanHelper = new MeanHelper();
    double meanX = meanHelper.meanOfXCoordinates(dataController.readLineDataSet());
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
    dataController = new DataController();
    meanHelper = new MeanHelper();
    double meanX = meanHelper.meanOfXCoordinates(dataController.readLineDataSet());
    double meanY = meanHelper.meanOfYCoordinates(dataController.readLineDataSet());
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
    return Math.atan(d);
  }

  /**
   * Computes a derivation formula for theta and for theta - 180 and it will return the result,
   * depending on whichever function renders positive.
   * @return The derivative function result.
   * @throws IllegalArgumentException Thrown when the result does not match the requirements.
   * @throws IOException Thrown at IOExeption.
   */
  public boolean computeFunctionOfT() throws IllegalArgumentException,IOException {
    dataController = new DataController();
    ArrayList<DataPoint> dc = dataController.readLineDataSet();
    double functionT = (sumOfYSquares(dc) - sumOfXSquares(dc)) * Math.cos(computeDTheta())
            - 2 * sumOfXYSquares(dc) * Math.sin(computeDTheta());
    double functionT180 = (sumOfYSquares(dc) - sumOfXSquares(dc)) * Math.cos(computeDTheta() + 180)
            - 2 * sumOfXYSquares(dc) * Math.sin(computeDTheta() + 180);

    if (functionT > 0) {
      return true;
    }
    else if (functionT180 > 0 ) {
      return false;
    }
    else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Computes the cosine value of t/2.
   * @return The cosine value of t/2.
   * @throws IOException Thrown at IOException.
   */
  public double computeA() throws IOException {
    dataController = new DataController();
    meanHelper = new MeanHelper();
    double a;
    double meanX = meanHelper.meanOfXCoordinates(dataController.readLineDataSet());
    double meanY = meanHelper.meanOfYCoordinates(dataController.readLineDataSet());
    if (computeFunctionOfT()) {
      a = Math.cos(computeDTheta()/2);
    }
    else {
      a = Math.cos((computeDTheta() +180) / 2);
    }

    return a;
  }

  /**
   * Computes the sin value of t/2.
   * @return The sin value of t/2.
   * @throws IOException Thrown at IOException.
   */
  public double computeB() throws IOException {
    dataController = new DataController();
    meanHelper = new MeanHelper();
    double b;
    double meanX = meanHelper.meanOfXCoordinates(dataController.readLineDataSet());
    double meanY = meanHelper.meanOfYCoordinates(dataController.readLineDataSet());
    if (computeFunctionOfT()) {
      b = Math.sin(computeDTheta()/2);
    }
    else {
      b = Math.sin((computeDTheta() +180) / 2);
    }

    return b;
  }

  /**
   * Computes the final model calculation of the best fitting line.
   * @return Final computed value for the best fitting line.
   * @throws IOException Thrown at IOException.
   */
  public double computeC() throws IOException {
    dataController = new DataController();
    meanHelper = new MeanHelper();

    double meanX = meanHelper.meanOfXCoordinates(dataController.readLineDataSet());
    double meanY = meanHelper.meanOfYCoordinates(dataController.readLineDataSet());
    double c = (-(computeA()) * meanX) - (computeB() * meanY);
    return c;
  }



}

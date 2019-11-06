package model;

import java.util.ArrayList;

import utility.MeanHelper;

/**
 * Class contains the methods required to compute the least squares line fitting technique to, get
 * the best fit of the line.
 */
public class LinearRegressionModelImpl implements ILinearRegressionModel {
  private ArrayList<DataPoint> dataPoints;
  private final double meanY;
  private final double meanX;
  private static double t = 0;

  public LinearRegressionModelImpl(ArrayList<DataPoint> dataPoints) {
    MeanHelper meanHelper = new MeanHelper();
    this.dataPoints = dataPoints;
    meanY = meanHelper.meanOfYCoordinates(dataPoints);
    meanX = meanHelper.meanOfXCoordinates(dataPoints);
  }


  /**
   * Computes the summation of the square of the Y coordinate of each DataPoint object subtracted,
   * from the the mean of all the Y coordinates.
   *
   * @param lineCoordinates The list of DataPoints.
   * @return The total summation.
   */
  public double sumOfYSquares(ArrayList<DataPoint> lineCoordinates) {
    double sum = 0;
    for (DataPoint d : lineCoordinates) {
      sum += ((d.getYCoordinate() - meanY) * (d.getYCoordinate() - meanY));
    }
    return sum;
  }

  /**
   * Computes the summation of the square of the X coordinate of each DataPoint object subtracted,
   * from the mean of all the X coordinates.
   *
   * @param lineCoordinates The list of DataPoints.
   * @return The total summation.
   */
  public double sumOfXSquares(ArrayList<DataPoint> lineCoordinates) {
    double sum = 0;
    for (DataPoint d : lineCoordinates) {
      sum += ((d.getXCoordinate() - meanX) * (d.getXCoordinate() - meanX));
    }
    return sum;
  }

  /**
   * Computes the summation of the product of the X coordinate subtracted from the mean of all the,
   * X coordinates and the Y coordinate subtracted from the mean of all the Y coordinates.
   *
   * @param lineCoordinates The list of DataPoints.
   * @return The total summation.
   */
  public double sumOfXYSquares(ArrayList<DataPoint> lineCoordinates) {
    double sum = 0;
    for (DataPoint d : lineCoordinates) {
      sum += (d.getXCoordinate() - meanX) * (d.getYCoordinate() - meanY);
    }
    return sum;
  }

  /**
   * Computes the tan inverse value of a formula computation that is required, to perform the least
   * squares operation.
   *
   * @return Tan inverse value.
   */
  public double computeDTheta() {
    double d = (2 * sumOfXYSquares(dataPoints)) / (sumOfXSquares(dataPoints) - sumOfYSquares(dataPoints));
    return Math.atan(d);
  }

  /**
   * Computes a derivation formula for theta and for theta - 180 and it will return the result,
   * depending on whichever function renders positive.
   *
   * @throws IllegalArgumentException Thrown when the result does not match the requirements.
   */
  public void computeFunctionOfT() throws IllegalArgumentException {
    double theta = computeDTheta();
    double functionT = ((sumOfYSquares(dataPoints) - sumOfXSquares(dataPoints)) * Math.cos(theta))
            - (2 * sumOfXYSquares(dataPoints) * Math.sin(theta));
    double functionT180 = ((sumOfYSquares(dataPoints) - sumOfXSquares(dataPoints)) * Math.cos(theta + Math.PI))
            - (2 * sumOfXYSquares(dataPoints) * Math.sin(theta + Math.PI));
    if (functionT > 0) {
      t = theta;
    } else if (functionT180 > 0) {
      t = theta + Math.PI;
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Computes the final model calculation of the best fitting line.
   *
   * @return Final computed value for the best fitting line.
   */
  public double computeXCoordinate() {
    computeFunctionOfT();
    System.out.println("a:"+Math.cos(t / 2));
    return Math.cos(t / 2);
  }

  public double computeYCoordinate() {
    System.out.println("b:"+Math.sin(t / 2));
    return Math.sin(t / 2);
  }

  public double computeC() {
    return (-1 * computeXCoordinate() * meanX) - (computeYCoordinate() * meanY);
  }


}

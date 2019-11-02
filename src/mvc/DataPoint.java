package mvc;

/**
 * A POJO class representing each data in the data set which is used in training the fitting algorithms
 */
public class DataPoint {
  double xCoordinate;
  double yCoordinate;

  public DataPoint(double xCoordinate,double yCoordinate) {
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
  }

  public double getXCoordinate() {
    return xCoordinate;
  }

  public double getYCoordinate() {
    return yCoordinate;
  }
}

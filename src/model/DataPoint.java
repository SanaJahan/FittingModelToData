package model;

/**
 * A POJO class representing each data in the data set which is used in training the fitting,
 * algorithms.
 */
public final class DataPoint {
  double xCoordinate;
  double yCoordinate;

  /**
   * Contructor for the DataPoint class that takes the X coordinate and Y coordinate as arguments.
   * @param xCoordinate X Coordinate.
   * @param yCoordinate Y Coordinate.
   */
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

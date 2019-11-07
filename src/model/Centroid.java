package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * POJO class to represent a Centroid of a cluster with its X and Y coordinates.
 */
public class Centroid {
  double xCoordinate;
  double yCoordinate;
  ArrayList<DataPoint> dataPoints;

  /**
   *Contructor that sets the x and y coordinates of the centroid.
   * @param xCoordinate X coordinate.
   * @param yCoordinate Y coordinate.
   */
  public Centroid(double xCoordinate, double yCoordinate) {
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
  }

  public double getxCoordinate() {
    return xCoordinate;
  }

  public void setxCoordinate(double xCoordinate) {
    this.xCoordinate = xCoordinate;
  }

  public double getyCoordinate() {
    return yCoordinate;
  }

  public void setyCoordinate(double yCoordinate) {
    this.yCoordinate = yCoordinate;
  }

  public ArrayList<DataPoint> getDataPoints() {
    return dataPoints;
  }

  public void setDataPoints(ArrayList<DataPoint> dataPoints) {
    this.dataPoints = dataPoints;
  }

  /**
   * Equals method for checking the centroid objects, overridden from the Object class.
   * @param o Object
   * @return True or False depending on the comparison.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Centroid centroid = (Centroid) o;
    return Double.compare(centroid.xCoordinate, xCoordinate) == 0 &&
            Double.compare(centroid.yCoordinate, yCoordinate) == 0 &&
            Objects.equals(dataPoints, centroid.dataPoints);
  }

  /**
   * Overridden from the Object class along with equals() to hash the returned value.
   * @return The hashed value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(xCoordinate, yCoordinate, dataPoints);
  }
}

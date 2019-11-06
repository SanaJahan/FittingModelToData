package model;

import java.util.ArrayList;
import java.util.Objects;

public class Centroid {
  double xCoordinate;
  double yCoordinate;
  ArrayList<DataPoint> dataPoints;

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

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Centroid centroid = (Centroid) o;
    return Double.compare(centroid.xCoordinate, xCoordinate) == 0 &&
            Double.compare(centroid.yCoordinate, yCoordinate) == 0 &&
            Objects.equals(dataPoints, centroid.dataPoints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(xCoordinate, yCoordinate, dataPoints);
  }
}

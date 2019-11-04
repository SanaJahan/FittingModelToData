package model;

import java.util.ArrayList;

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
}

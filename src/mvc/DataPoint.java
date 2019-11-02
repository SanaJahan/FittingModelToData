package mvc;

/**
 * A POJO class representing each data in the data set which is used in training the fitting algorithms
 */
public class DataPoint {
  int xCoordinate;
  int yCoordinate;

  public DataPoint(int xCoordinate,int yCoordinate) {
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
  }

  public int getxCoordinate() {
    return xCoordinate;
  }

  public int getyCoordinate() {
    return yCoordinate;
  }
}

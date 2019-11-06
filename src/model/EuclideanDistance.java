package model;

public class EuclideanDistance implements Distance {

  @Override
  public double calculate(DataPoint d1, Centroid d2) {
    double sum = 0;
    double px1 = d1.getXCoordinate();
    double py1 = d1.getYCoordinate();
    double px2 = d2.getxCoordinate();
    double py2 = d2.getyCoordinate();
    double diff1 = Math.pow(px1 - px2, 2);
    double diff2 = Math.pow(py1 - py2, 2);
    sum += Math.pow(diff1 - diff2, 2);
    return Math.sqrt(sum);
  }
}

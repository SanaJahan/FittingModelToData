package mvc;

public interface ILinearRegressionModel {

  double squareOfXCoordinates(double meanOfXCoordinates);

  double squareOfYCoordinates(double meanOfYCoordinates);

  double squareOfXYCoordinates(double meanOfXYCoordinates);

  double computeD();

  double computeTheta();

  double computeModel(int t) throws IllegalArgumentException;// throws exception if f(t) is negative due to t

  double computeCos(int t);

  double computeSin(int t);

  double computeC();
}

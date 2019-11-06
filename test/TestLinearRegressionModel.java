import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import model.DataPoint;
import model.ILinearRegressionModel;
import model.LinearRegressionModelImpl;

import static org.junit.Assert.assertEquals;


public class TestLinearRegressionModel {

  ILinearRegressionModel linear;

  ArrayList<DataPoint> dataSet = new ArrayList<>();
  @Before
  public void setUp() {
    dataSet.add(new DataPoint(1,3));
    dataSet.add(new DataPoint(2,3));
    dataSet.add(new DataPoint(56,9));
    dataSet.add(new DataPoint(6,4));
    dataSet.add(new DataPoint(4,9));
    dataSet.add(new DataPoint(7,-6));
    dataSet.add(new DataPoint(6,-34));
    dataSet.add(new DataPoint(8,9));
    dataSet.add(new DataPoint(-2,0));
    dataSet.add(new DataPoint(8,7));
    dataSet.add(new DataPoint(42,6));
    dataSet.add(new DataPoint(0,2));
    dataSet.add(new DataPoint(7,-9));
    dataSet.add(new DataPoint(1,0));
    dataSet.add(new DataPoint(-1,3));

  }

  @Test
  public void linearComputeC() throws IOException {
    linear = new LinearRegressionModelImpl(dataSet);
    assertEquals(1.9641398138345147,linear.computeC(),6);
  }

  @Test
  public void linearComputeA() throws IOException {
    linear = new LinearRegressionModelImpl(dataSet);
    assertEquals(-0.24332255024518284,linear.computeXCoordinate(),6);
  }

  @Test
  public void linearComputeB() throws IOException {
    linear = new LinearRegressionModelImpl(dataSet);
    assertEquals(0.9699454296722989,linear.computeYCoordinate(),6);
  }

  @Test
  public void linearComputeDTheta() throws IOException {
    linear = new LinearRegressionModelImpl(dataSet);
    assertEquals(0.4915797788314696,linear.computeDTheta(),6);
  }

  @Test
  public void linearComputeSumXY() throws IOException {
    linear = new LinearRegressionModelImpl(dataSet);
    assertEquals(583.0,linear.sumOfXYSquares(dataSet),6);
  }

  @Test
  public void linearComputeSumXSquares() throws IOException {
    linear = new LinearRegressionModelImpl(dataSet);
    assertEquals(3823.3333333333344,linear.sumOfXSquares(dataSet),6);
  }

  @Test
  public void linearComputeSumYSquares() throws IOException {
    linear = new LinearRegressionModelImpl(dataSet);
    assertEquals(1645.6,linear.sumOfYSquares(dataSet),6);
  }


}

import org.junit.Test;

import java.io.IOException;

import controller.DataController;
import model.ILinearRegressionModel;
import model.LinearRegressionModelImpl;


public class TestLinearRegressionModel {

  ILinearRegressionModel linear;
  DataController dc;


  @Test
  public void linearDataOneComputeC() throws IOException {
    dc = new DataController("linedata-1");
    linear = new LinearRegressionModelImpl(dc.getDataPoints());
    linear.computeC();
  }

  @Test
  public void linearDataTwoComputeC() throws IOException {
    dc = new DataController("linedata-2");
    linear = new LinearRegressionModelImpl(dc.getDataPoints());
    linear.computeC();
  }

  @Test
  public void linearDataThreeComputeC() throws IOException {
    dc = new DataController("linedata-3");
    linear = new LinearRegressionModelImpl(dc.getDataPoints());
    linear.computeC();
  }

  @Test
  public void linearDataOneComputeA() throws IOException {
    dc = new DataController("linedata-1");
    linear = new LinearRegressionModelImpl(dc.getDataPoints());
    linear.computeXCoordinate();
  }

  @Test
  public void linearDataTwoComputeA() throws IOException {
    dc = new DataController("linedata-2");
    linear = new LinearRegressionModelImpl(dc.getDataPoints());
    linear.computeXCoordinate();
  }

  @Test
  public void linearDataThreeComputeA() throws IOException {
    dc = new DataController("linedata-3");
    linear = new LinearRegressionModelImpl(dc.getDataPoints());
    linear.computeXCoordinate();
  }

  @Test
  public void linearDataOneComputeB() throws IOException {
    dc = new DataController("linedata-1");
    linear = new LinearRegressionModelImpl(dc.getDataPoints());
    linear.computeYCoordinate();
  }

  @Test
  public void linearDataTwoComputeB() throws IOException {
    dc = new DataController("linedata-2");
    linear = new LinearRegressionModelImpl(dc.getDataPoints());
    linear.computeYCoordinate();
  }

  @Test
  public void linearDataThreeComputeB() throws IOException {
    dc = new DataController("linedata-3");
    linear = new LinearRegressionModelImpl(dc.getDataPoints());
    linear.computeYCoordinate();
  }

}

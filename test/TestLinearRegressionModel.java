import org.junit.Test;

import java.io.IOException;

import controller.DataController;
import model.ILinearRegressionModel;
import model.LinearRegressionModelImpl;


public class TestLinearRegressionModel {

  ILinearRegressionModel linear;
  DataController dc;


  @Test
  public void linearModelTestOne() throws IOException {
    dc = new DataController();
    dc.readDataSet("lineardata")
    linear = new LinearRegressionModelImpl();
  }

}

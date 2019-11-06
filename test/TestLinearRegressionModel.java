import org.junit.Test;

import java.io.IOException;

import model.ILinearRegressionModel;
import model.LinearRegressionModelImpl;


public class TestLinearRegressionModel {

  ILinearRegressionModel linear;


  @Test
  public void linearModelTestOne() throws IOException {
    linear = new LinearRegressionModelImpl();
  }

}

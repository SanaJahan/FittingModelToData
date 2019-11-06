import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import controller.DataController;
import model.DataPoint;
import model.ILinearRegressionModel;
import model.LinearRegressionModelImpl;


public class TestLinearRegressionModel {

  ILinearRegressionModel linear;
  DataController dc = new DataController();


  @Test
  public void linearModelTestOne() throws IOException {
    dc = new DataController();
    ArrayList<DataPoint> dataset = dc.readDataSet("linedata-1");
    linear = new LinearRegressionModelImpl(dataset);
  }

}

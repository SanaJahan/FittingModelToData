import org.junit.Test;

import java.io.IOException;

import controller.DataController;
import model.LinearRegressionModelImpl;

import static org.junit.Assert.assertEquals;

public class TestDataController {

  LinearRegressionModelImpl linear;
  DataController dataController;

  @Test
  public void getDataPointsTest() throws IOException {
    dataController = new DataController("linedata-1");
    dataController.getDataPoints();
    //need to change this test to check the size
  }

  @Test
  public void linearBestFitSizeTestOne() throws IOException {
    dataController = new DataController("linedata-1");
    assertEquals(4,dataController.getLinearBestFit().size());
  }

  @Test
  public void linearBestFitSizeTestTwo() throws IOException {
    dataController = new DataController("linedata-2");
    assertEquals(4,dataController.getLinearBestFit().size());
  }

  @Test
  public void linearBestFitSizeTestThree() throws IOException {
    dataController = new DataController("linedata-3");
    assertEquals(4,dataController.getLinearBestFit().size());
  }


}

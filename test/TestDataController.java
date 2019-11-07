import org.junit.Test;

import java.io.IOException;

import controller.DataController;

import static org.junit.Assert.assertEquals;

/**
 * Tester class to test the methods of the Controller class.
 */
public class TestDataController {

  DataController dataController;

  @Test
  public void getDataSetOneSizeTest() throws IOException {
    dataController = new DataController("linedata-1");
    int size = dataController.getDataPoints().size();
    assertEquals(396,size);
  }

  @Test
  public void getDataSetTwoSizeTest() throws IOException {
    dataController = new DataController("linedata-2");
    int size = dataController.getDataPoints().size();
    assertEquals(378,size);
  }

  @Test
  public void getDataSetThreeSizeTest() throws IOException {
    dataController = new DataController("linedata-3");
    int size = dataController.getDataPoints().size();
    assertEquals(396,size);
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

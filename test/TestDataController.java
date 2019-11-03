import org.junit.Test;

import java.io.IOException;

import controller.DataController;

import static org.junit.Assert.assertEquals;

public class TestDataController {

  DataController dataController = new DataController();

  @Test
  public void checkClusterDataPointListSize() throws IOException {
    assertEquals(4500,dataController.readClusterDataSet().size());
  }

  @Test
  public void checkLinearDataPointListSize() throws IOException {
    assertEquals(1170,dataController.readLineDataSet().size());
  }

}

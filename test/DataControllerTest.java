import org.junit.Test;

import java.io.IOException;

import mvc.DataController;

import static org.junit.Assert.assertEquals;

public class DataControllerTest {

  DataController dataController = new DataController();

  @Test
  public void checkClusterDataPointListSize() throws IOException {
    assertEquals(4500,dataController.readClusterDataset().size());
  }

  @Test
  public void checkLinearDataPointListSize() throws IOException {
    assertEquals(1170,dataController.readLinearDataset().size());
  }

}
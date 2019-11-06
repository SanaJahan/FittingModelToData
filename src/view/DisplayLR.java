package view;

import java.io.IOException;

import controller.DataController;
import model.DataPoint;

public class DisplayLR {

  private static DataController dataController = new DataController();



  public static void main(String[] args) throws IOException {
    displayLinearRegression();
  }
  public static void displayLinearRegression() throws IOException {
    ImagePlotter plotter = new ImagePlotter();
    plotter.setWidth(500);
    plotter.setHeight(500);

    plotter.setDimensions(-800,800,-850,800);

    plotter.addLine(-300,(int)Math.floor(dataController.getLinearBestFit().get(1)),
            (int)Math.floor(dataController.getLinearBestFit().get(0)),300);
      for (DataPoint d : dataController.getDataPoints())
        plotter.addPoint((int) Math.floor(d.getXCoordinate()), (int) Math.floor(d.getYCoordinate()));


    try {
      plotter.write("lr.png");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}

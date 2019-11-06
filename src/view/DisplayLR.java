package view;

import java.io.IOException;

import controller.DataController;
import model.DataPoint;

public class DisplayLR {
  public static void main(String[] args) throws IOException {
    displayLinearRegression();
  }
  public static void displayLinearRegression() throws IOException {
    DataController dataController = new DataController();
    ImagePlotter plotter = new ImagePlotter();
    plotter.setWidth(500);
    plotter.setHeight(500);

    plotter.setDimensions(-300,800,-350,800);

    plotter.addLine(-300,(int)Math.floor(dataController.report().get(1)),
            (int)Math.floor(dataController.report().get(0)),300);
      for (DataPoint d : dataController.readLineDataSet()){
        plotter.addPoint((int) Math.floor(d.getXCoordinate()), (int) Math.floor(d.getYCoordinate()));
      }


    try {
      plotter.write("lr.png");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}

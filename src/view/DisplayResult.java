package view;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import controller.DataController;
import model.Centroid;
import model.DataPoint;

public class DisplayResult {

  public static void main(String[] args) throws IOException {
     displayClusterMean();
  }
  public static void displayClusterMean() throws IOException {
    DataController dataController = new DataController();
    ImagePlotter plotter = new ImagePlotter();
    plotter.setWidth(400);
    plotter.setHeight(400);

    plotter.setDimensions(-300,800,-350,800);

    Color[] color = {Color.CYAN,Color.RED,Color.BLACK,Color.BLUE,Color.GREEN, Color.LIGHT_GRAY};
    int i = 0;
    ArrayList<Centroid> centroids = dataController.reportResult();
    for (Centroid c: centroids) {
      plotter.addPoint((int)Math.floor(c.getxCoordinate()),(int)Math.floor(c.getyCoordinate()),Color.YELLOW);
      if(c.getDataPoints() != null) {
        for (DataPoint d : c.getDataPoints()) {
          plotter.addPoint((int) Math.floor(d.getXCoordinate()), (int) Math.floor(d.getYCoordinate()), color[i]);
        }
      }
      i++;
    }

    try {
      plotter.write("kMeans7.png");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
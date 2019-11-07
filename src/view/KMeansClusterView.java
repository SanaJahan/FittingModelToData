package view;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import controller.DataController;
import model.Centroid;
import model.DataPoint;

/**
 * A view class for plotting the KMeans clusters.
 */
public class KMeansClusterView {

  /**
   * Main class for the execution of the method in this class.
   * @param args main static method args.
   * @throws IOException Thrown at IOException.
   */
  public static void main(String[] args) throws IOException {
     displayClusterMean();
  }

  /**
   * For performing the plotting operation.
   * @throws IOException Thrown at IOException.
   */
  public static void displayClusterMean() throws IOException {
    DataController dataController = new DataController("cluster");
    ImagePlotter plotter = new ImagePlotter();
    plotter.setWidth(400);
    plotter.setHeight(400);

    plotter.setDimensions(-300,800,-350,800);

    Color[] color = {Color.CYAN,Color.RED,Color.BLACK,Color.BLUE,Color.GREEN, Color.LIGHT_GRAY};
    int i = 0;
    ArrayList<Centroid> centroids = dataController.getKMeansCluster();
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
      plotter.write("kMeans13.png");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}

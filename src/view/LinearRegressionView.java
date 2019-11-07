package view;

import java.io.IOException;
import java.util.List;

import controller.DataController;
import model.DataPoint;

/**
 * View class for plotting the fit of the best line using linear regression.
 */
public class LinearRegressionView {

  private static DataController dataController;

  static {
    try {
      dataController = new DataController("linedata-1");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Main class for the execution of the method in this class.
   * @param args main static method args.
   * @throws IOException Thrown at IOException.
   */
  public static void main(String[] args) throws IOException {
    displayLinearRegression();
  }

  /**
   * For performing the plotting of the best line.
   * @throws IOException Thrown at IOException.
   */
  public static void displayLinearRegression() throws IOException {
    ImagePlotter plotter = new ImagePlotter();
    plotter.setWidth(500);
    plotter.setHeight(500);

    plotter.setDimensions(-800, 800, -850, 800);

    List<Double> linearBestFit = dataController.getLinearBestFit();
    int y1 = (int) (Math.floor(linearBestFit.get(1) - (linearBestFit.get(2) * -300)
            / linearBestFit.get(3)));
    int x2 = (int) (Math.floor(linearBestFit.get(0) - (linearBestFit.get(3) * 300)
            / linearBestFit.get(2)));

    plotter.addLine(-300, y1, x2, 300);
    for (DataPoint d : dataController.getDataPoints()) {
      plotter.addPoint((int) Math.floor(d.getXCoordinate()), (int) Math.floor(d.getYCoordinate()));
    }


    try {
      plotter.write("linear.png");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}

package view;

import java.io.IOException;
import java.util.ArrayList;

import model.DataPoint;
import model.LinearRegressionModelImpl;

public class LinearRegressionView {

  public double lineEquation(ArrayList<DataPoint> dataPoints) throws IOException {
    LinearRegressionModelImpl linear = new LinearRegressionModelImpl();
    double equation = 0;
    for (DataPoint d:dataPoints) {
      equation = (linear.computeA() * d.getXCoordinate()) + (linear.computeB() *
              d.getYCoordinate()) + linear.computeC();
    }
  }

  public  void main() {
    ImagePlotter plotter = new ImagePlotter();
    plotter.setWidth(400);
    plotter.setHeight(400);

    plotter.setDimensions(-300,300,-350,350);
    for (int x = -200; x < 200; x += 20) {
      for (int y = 0; y <= x; y += 20) {
        plotter.addCircle(x, y, 10);
        plotter.addPoint(x, y);
        plotter.addLine(x, y, x + 20, y);
        plotter.addLine(x, y, x, y + 20);
      }
    }

    try {
      plotter.write("example.png");
    } catch (IOException e) {

    }
  }
}

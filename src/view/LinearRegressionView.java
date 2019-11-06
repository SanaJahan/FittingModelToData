package view;

import java.io.IOException;
import java.util.ArrayList;

import controller.DataController;
import model.DataPoint;
import model.LinearRegressionModelImpl;
import utility.MeanHelper;

public class LinearRegressionView {

  public static void main(String[] args) throws IOException {
    displayLinearPlot();
  }
  public static void displayLinearPlot() throws IOException {;
    DataController dataController = new DataController();
    ArrayList<DataPoint> dataPoints = dataController.readLineDataSet();
    ImagePlotter plotter = new ImagePlotter();
    plotter.setWidth(400);
    plotter.setHeight(400);

    plotter.setDimensions(-300,300,-350,350);
    LinearRegressionModelImpl linear = new LinearRegressionModelImpl();
    MeanHelper meanHelper = new MeanHelper();
    for (DataPoint d:dataPoints) {
//      int x = (int)Math.floor(linear.computeA() * d.getXCoordinate() + linear.computeC());
//      int y = (int)Math.floor(linear.computeB() * d.getYCoordinate() + linear.computeC());
      plotter.addPoint((int) Math.floor(d.getXCoordinate()), (int) Math.floor(d.getYCoordinate()));
    }
//    int x = (int)Math.floor(linear.computeA()*meanHelper.meanOfXCoordinates(dataPoints)) ;
//    int y = (int)Math.floor(linear.computeB()*meanHelper.meanOfYCoordinates(dataPoints));
//    plotter.addLine(-300,y,x,300);

    try {
      plotter.write("lineplot.png");
    } catch (IOException e) {

    }
  }
}

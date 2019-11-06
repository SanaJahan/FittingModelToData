package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Centroid;
import model.DataPoint;
import model.EuclideanDistance;
import model.IKMeansClusteringModel;
import model.ILinearRegressionModel;
import model.KMeansClusteringModelImpl;
import model.LinearRegressionModelImpl;

/**
 * This class has methods to read and manipulate data from the data set. Then the data set is sent
 * to the respective fitting algorithms, which will process the data and give back the result to
 * this controller itself.
 */
public class DataController extends AbstractDataController {

  public static final int K = 5;
  public static final int MAX_ITERATIONS = 1000;
  private IKMeansClusteringModel kMeansClusteringModel;
  private ILinearRegressionModel linearRegressionModel;
  private ArrayList<DataPoint> dataPoints;


  /**
   * Reads each dataset file and will create the DataPoint objects from the coordinates, mentioned
   * in each line of those files. They will be separated into a list of data points.
   *
   * @throws IOException IOException may be thrown.
   */
  @Override
  public void readDataSet(String fileName) throws IOException {
    dataPoints = readFiles(fileName);
  }


@Override
public int getDataSize() {
    return dataPoints.size();
}

  public ArrayList<DataPoint> getDataPoints() {
    return dataPoints;
  }

  // return the k-means result
  public ArrayList<Centroid> getKMeansCluster() throws IOException {
    kMeansClusteringModel = new KMeansClusteringModelImpl();
    return kMeansClusteringModel.bestFit(dataPoints, K, new EuclideanDistance(), MAX_ITERATIONS);
  }


  public List<Double> getLinearBestFit() throws IOException {
    linearRegressionModel = new LinearRegressionModelImpl(dataPoints);
    List<Double> list = new ArrayList<>();
    double c = linearRegressionModel.computeC();
    list.add(c / linearRegressionModel.computeXCoordinate());
    list.add(c / linearRegressionModel.computeYCoordinate());
    return list;
  }

}

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

  public static final int K = 3;
  public static final int MAX_ITERATIONS = 1000;
  private IKMeansClusteringModel kMeansClusteringModel;
  private ILinearRegressionModel linearRegressionModel;
  private ArrayList<DataPoint> dataPoints;

  /**
   * Constructor that takes the input filename as string.
   * @param input File name input string.
   * @throws IOException Thrown at IOException.
   */
  public DataController(String input) throws IOException {
    readDataSet(input);
  }


  /**
   * Reads each dataset file and will create the DataPoint objects from the coordinates, mentioned
   * in each line of those files. They will be separated into a list of data points.
   *
   * @throws IOException IOException may be thrown.
   */
  @Override
  public void readDataSet(String fileName) throws IOException {
    dataPoints = readFiles(fileName);
    if(dataPoints.size() <= 0) {
      throw new IOException("No data points exist for the given filename");
    }
  }

  /**
   * Gets a list of the data points.
   * @return List of data points.
   */
  @Override
  public ArrayList<DataPoint> getDataPoints() {
    return dataPoints;
  }

  /**
   * Computes the final KMeans result from the list of centroids.
   * @return The final list of centroids.
   */
  @Override
  public ArrayList<Centroid> getKMeansCluster() {
    kMeansClusteringModel = new KMeansClusteringModelImpl();
    return kMeansClusteringModel.bestFit(dataPoints, K, new EuclideanDistance(), MAX_ITERATIONS);
  }

  /**
   * Creates a list of formula computations that would be used in the View class to compute the,
   * best fitting line.
   * @return List of formuls computed results.
   * @throws IOException Thrown at IOException.
   */
  @Override
  public List<Double> getLinearBestFit() throws IOException {
    linearRegressionModel = new LinearRegressionModelImpl(dataPoints);
    List<Double> list = new ArrayList<>();
    double c = linearRegressionModel.computeC();
    list.add((-1 * c) / linearRegressionModel.computeXCoordinate()); //0
    list.add((-1 * c) / linearRegressionModel.computeYCoordinate()); //1
    list.add(linearRegressionModel.computeXCoordinate()); //2
    list.add(linearRegressionModel.computeYCoordinate()); //3

    return list;
  }

}

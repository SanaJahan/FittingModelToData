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
   * Constructor for the DataController class which takes the fileName.
   * @param input The fileName to be parsed.
   * @throws IOException Thrown at IOException.
   */
  public DataController(String input) throws IOException {
    readDataSet(input);
  }

  /**
   * Reads each dataset file and will create the DataPoint objects from the coordinates, mentioned
   * in each line of those files. They will be separated into a list of data points.
   * @throws IOException IOException may be thrown.
   */
  @Override
  public void readDataSet(String fileName) throws IOException {
    dataPoints = readFiles(fileName);
  }

  /**
   * Retrieves the list of all the datapoints.
   * @return List of datapoints.
   */
  public ArrayList<DataPoint> getDataPoints() {
    System.out.println(dataPoints);
    return dataPoints;
  }

  /**
   * Returns the final list of centroids for the KMeans algorithm.
   * @return A list of the centroids.
   * @throws IOException Thrown at IOException.
   */
  public ArrayList<Centroid> getKMeansCluster() throws IOException {
    kMeansClusteringModel = new KMeansClusteringModelImpl();
    return kMeansClusteringModel.bestFit(dataPoints, K, new EuclideanDistance(), MAX_ITERATIONS);
  }


  /**
   * List of formula outputs to be used in the view class.
   * @return The list of formula outputs.
   * @throws IOException Thrown at IOException.
   */
  public List<Double> getLinearBestFit() throws IOException {
    linearRegressionModel = new LinearRegressionModelImpl(dataPoints);
    List<Double> list = new ArrayList<>();
    double c = linearRegressionModel.computeC();

    list.add((-1 * c) / linearRegressionModel.computeXCoordinate()); //0
    list.add((-1 * c) / linearRegressionModel.computeYCoordinate()); //1
    list.add(linearRegressionModel.computeXCoordinate()); //2
    list.add(linearRegressionModel.computeYCoordinate()); //3

    System.out.println(list.size());
    return list;
  }

}

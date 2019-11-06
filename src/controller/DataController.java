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

  private IKMeansClusteringModel kMeansClusteringModel;
  private ILinearRegressionModel linearRegressionModel;

  /**
   * Reads each dataset file and will create the DataPoint objects from the coordinates, mentioned
   * in each line of those files. They will be separated into a list of data points.
   * @throws IOException IOException may be thrown.
   */
  public ArrayList readClusterDataSet() throws IOException {
    ArrayList<DataPoint> dataPoints = readFiles("clusterdata-");
    return dataPoints;
  }


  /**
   * Reads each dataset file and will create the DataPoint objects from the coordinates, mentioned
   * in each line of those files. They will be separated into a list of data points.
   * @throws IOException IOException may be thrown.
   */
  public ArrayList<DataPoint> readLineDataSet() throws IOException {
    ArrayList<DataPoint> dataPoints = readFiles("linedata-1");
    return dataPoints;
  }

  // return the k-means result
  public ArrayList<Centroid> reportResult() throws IOException {
    kMeansClusteringModel = new KMeansClusteringModelImpl();
    return kMeansClusteringModel.fit(readClusterDataSet(),5,new EuclideanDistance(),3000);
  }

  public List<Double> report() throws IOException {
    linearRegressionModel = new LinearRegressionModelImpl();
    List<Double> list = new ArrayList<>();
    double c = linearRegressionModel.computeC();
    list.add(c/linearRegressionModel.computeXCoordinate());
    list.add(c/linearRegressionModel.computeYCoordinate());
    return list;
  }

}

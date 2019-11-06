package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Centroid;
import model.DataPoint;

public abstract class AbstractDataController {


  public abstract void readDataSet(String fileName) throws IOException;

  public abstract ArrayList<Centroid> getKMeansCluster();

  public abstract List<Double> getLinearBestFit() throws IOException;
  
  public abstract ArrayList<DataPoint> getDataPoints();

  /**
   * method to read dataset files for specific category of data.
   * @param category represents the dataset category whether clusterData or lineData
   * @return the list of files for the given category of data
   */

  protected File[] createFiles(String category) {
    String target_dir = "./data";
    File dir = new File(target_dir);
    File[] files = dir.listFiles((dir1, name) -> {
      boolean result;
      result = name.startsWith(category);
      return result;
    });
    return files;
  }


  /**
   * Reads each dataset file and will create the DataPoint objects from the coordinates, mentioned
   * in each line of those files. They will be separated into a list of data points.
   * @throws IOException IOException may be thrown.
   */
  protected ArrayList<DataPoint> readFiles(String category) throws IOException {
    ArrayList<DataPoint> dataPoints = new ArrayList<>();
    for (File f : createFiles(category)) {
      BufferedReader inputStream = null;
      try {
        inputStream = new BufferedReader(
                new FileReader(f));
        String line;
        while ((line = inputStream.readLine()) != null) {
          String[] coordinates = line.split(" ");
          double x = Double.parseDouble(coordinates[0]);
          double y = Double.parseDouble(coordinates[1]);
          DataPoint dataPoint = new DataPoint(x, y);
          dataPoints.add(dataPoint);
        }
      } finally {
        if (inputStream != null) {
          inputStream.close();
        }
      }
    }
    return dataPoints;
  }

}

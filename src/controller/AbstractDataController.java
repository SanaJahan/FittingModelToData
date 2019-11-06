package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Centroid;
import model.DataPoint;

public abstract class AbstractDataController {


  public abstract void readDataSet(String fileName) throws IOException;

  public abstract ArrayList<Centroid> getKMeansCluster() throws IOException;
  public  abstract int getDataSize();

  /**
   * method to read dataset files for specific category of data.
   * @param category represents the dataset category whether clusterData or lineData
   * @return the list of files for the given category of data
   */

  public File[] createFiles(String category) {
    String target_dir = "./data";
    File dir = new File(target_dir);
    File[] files = dir.listFiles((dir1, name) -> {
      boolean result;
      if (name.startsWith(category)) {
        result = true;
      } else {
        result = false;
      }
      return result;
    });
    return files;
  }


  /**
   * Reads each dataset file and will create the DataPoint objects from the coordinates, mentioned
   * in each line of those files. They will be separated into a list of data points.
   * @throws IOException IOException may be thrown.
   */
  public ArrayList<DataPoint> readFiles(String category) throws IOException {
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

package mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class has methods to read and manipulate data from the data set.
 * Then the data set is sent to the respective fitting algorithms, which will
 * process the data and give back the result to this controller itself.
 */
public class DataController {

   //So now the Controller has two methods. One returns the cluster data points and the other,
   //returns the linear data points.
   //Both of these methods can be called by the respective algorithmic class and used accordingly.
   //Also need to find the right directory path.


  /**
   * Creates a File list from the data folder.
   * @return List of individual files.
   */
  public File[] createFiles() {
    String target_dir = "data";
    File dir = new File(target_dir);
    File[] files = dir.listFiles();
    return files;
  }

  /**
   * Reads each cluster dataset file and will create the DataPoint objects from the coordinates,
   * mentioned in each line of those files.
   * They will be separated into a list of cluster data points.
   * @throws IOException IOException may be thrown.
   */
  public ArrayList readClusterDataset() throws IOException {

    ArrayList<DataPoint> clusterDataPoints = new ArrayList<>();

    for (File f : createFiles()) {
      if(f.isFile() && f.getName().contains("cluster")) {
        BufferedReader inputClusterStream = null;

        try {
          inputClusterStream = new BufferedReader(
                  new FileReader(f));
          String line;

          while ((line = inputClusterStream.readLine()) != null) {
            String[] coordinates = line.split(" ");
            double x = Double.parseDouble(coordinates[0]);
            double y = Double.parseDouble(coordinates[1]);
            DataPoint dataPoint = new DataPoint(x,y);
            clusterDataPoints.add(dataPoint);
          }
        }
        finally {
          if (inputClusterStream != null) {
            inputClusterStream.close();
          }
        }
      }
    }
    return clusterDataPoints;
  }

  /**
   * Reads each line dataset file and will create the DataPoint objects from the coordinates,
   * mentioned in each line of those files.
   * They will be separated into a list of linear data points.
   * @throws IOException IOException may be thrown.
   */
  public ArrayList readLinearDataset() throws IOException {
    ArrayList<DataPoint> linearDataPoints = new ArrayList<>();

    for (File f : createFiles()) {
      if(f.isFile() && f.getName().contains("line")) {
        BufferedReader inputLinearStream = null;

        try {
          inputLinearStream = new BufferedReader(
                  new FileReader(f));
          String line;

          while ((line = inputLinearStream.readLine()) != null) {
            String[] coordinates = line.split(" ");
            double x = Double.parseDouble(coordinates[0]);
            double y = Double.parseDouble(coordinates[1]);
            DataPoint dataPoint = new DataPoint((int)x,(int)y);
            linearDataPoints.add(dataPoint);
          }
        }
        finally {
          if (inputLinearStream != null) {
            inputLinearStream.close();
          }
        }
      }
    }
    return linearDataPoints;
  }

}

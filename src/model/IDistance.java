package model;

/**
 * Interface for the EuclideanDistance class that holds a method for the distance calculation.
 */
public interface IDistance {
  double calculate(DataPoint d1, Centroid d2);
}

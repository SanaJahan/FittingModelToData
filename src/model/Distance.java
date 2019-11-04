package model;

import java.io.IOException;

public interface Distance {
  double calculate(DataPoint d1, Centroid d2) throws IOException;
}

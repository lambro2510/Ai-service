package com.lambro2510.service.Utils;

public class Helper {
  public static boolean isMaxValueDoubled(double[] outcomes) {
    if (outcomes == null || outcomes.length == 0) {
      return false;
    }

    double max = Double.MIN_VALUE;

    for (double value : outcomes) {
      if (value > max) {
        max = value;
      }
    }

    for (double value : outcomes) {
      if (value != max && value * 2 <= max) {
        return false;
      }
    }

    return true;
  }
}

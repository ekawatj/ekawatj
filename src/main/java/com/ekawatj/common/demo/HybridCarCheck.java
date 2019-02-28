package com.ekawatj.common.demo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ejirapongpan on 12/14/15.
 */
class HybridCarCheck {

  static List<String> hybridCars = Arrays.asList("Prius","CT200h","RX300h","Rx350h");

  public static boolean isHybridCar(String model) {
    for (String car : hybridCars) {
      if (car.equalsIgnoreCase(model)) {
        System.out.println("Match Found.");
        return true;
      }
    }
    System.out.println("....Not Found...");
    return false;
  }

  public static void main (String[] args) {
    isHybridCar("prius");
  }

}

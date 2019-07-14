public static double fixLocationCoord(double value){
  return ((long)(value * 1000000)) / 1000000.0;
}

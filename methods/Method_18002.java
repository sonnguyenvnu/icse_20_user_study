public static boolean hasMultipleCores(){
  final int numCores=getNumberOfCPUCores();
  return numCores != DEVICEINFO_UNKNOWN && numCores > 1;
}

private static String named(String histogramName,String statName){
  return histogramName + ScopedRegistry.DEFAULT_SCOPE_DELIMITER + statName;
}

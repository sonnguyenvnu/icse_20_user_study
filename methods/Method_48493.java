private static void discardRange(KeyRange local){
  log.warn("Individual key range is too small for partition block - result would be empty; hence ignored: {}",local);
}

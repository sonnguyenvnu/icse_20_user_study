private String statsKey(String key){
  return suppressedState == SuppressedState.SUPPRESSED ? key + "-suppressed" : key;
}

public String[] getParameterNamesForMethod(){
  if (collector == null || !collector.debugInfoPresent) {
    return new String[0];
  }
  return collector.getResult().split(",");
}

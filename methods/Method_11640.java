@Override public void initParam(String[] extra){
  if (extra != null && !(extra.length == 1 && extra[0].length() == 0)) {
    datePatterns=extra;
  }
}

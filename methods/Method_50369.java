@Override public List<String> list(){
  return Splitter.on(",").splitToList(appNameList);
}

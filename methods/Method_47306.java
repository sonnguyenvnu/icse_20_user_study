public int getListOrGridForPath(String path,int defaultValue){
  Integer value=filesGridOrList.getValueForLongestKeyPrefixing(path);
  return value != null ? value : defaultValue;
}

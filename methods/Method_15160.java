public boolean isContain(String tag){
  return tag != null && refreshMap != null && refreshMap.containsKey(tag);
}

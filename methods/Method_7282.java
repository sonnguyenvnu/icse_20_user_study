private static void insertValuesInMap(Map<String,Integer> map,String[] values){
  if (values == null) {
    return;
  }
  for (int i=0; i < values.length; ++i) {
    if (values[i] != null && values[i].length() > 0) {
      map.put(values[i],i);
    }
  }
}

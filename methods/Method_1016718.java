void populate(HashMap<String,String> headers){
  map.clear();
  headers.forEach((key,value) -> map.put(key,value));
  populate();
}

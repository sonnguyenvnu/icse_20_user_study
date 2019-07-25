public Map<String,Object> sensitive(){
  NutMap map=new NutMap();
  for (  String key : keys) {
    map.put(key,get(key));
  }
  return map;
}

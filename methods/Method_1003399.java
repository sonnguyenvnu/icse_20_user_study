private void increment(HashMap<String,Integer> map,String key){
  Integer value=map.get(key);
  value=Integer.valueOf(value == null ? 0 : value + 1);
  map.put(key,value);
  contextCount=10;
}

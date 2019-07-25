public void set(String key,String value,int timestamp){
  map.putIfAbsent(key,new TreeMap<>());
  TreeMap<Integer,String> treeMap=map.get(key);
  treeMap.put(timestamp,value);
}

protected static Map<String,Object> entryToMap(Entry<String,Integer> entry){
  Map<String,Object> map=new HashMap<>();
  map.put("v",entry.getKey());
  map.put("c",entry.getValue());
  return map;
}

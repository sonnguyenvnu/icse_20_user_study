@JsonIgnore private Map<String,Integer> add(Map<String,Integer> map,String processorId,Integer count){
  if (!map.containsKey(processorId)) {
    map.put(processorId,count);
  }
 else {
    Integer newCount=map.get(processorId) + count;
    map.put(processorId,newCount);
  }
  return map;
}

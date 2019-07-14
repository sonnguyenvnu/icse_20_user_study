private Map<String,Float> top(int size,Map<String,Float> map){
  Map<String,Float> result=new LinkedHashMap<String,Float>();
  for (  Map.Entry<String,Float> entry : new MaxHeap<Map.Entry<String,Float>>(size,new Comparator<Map.Entry<String,Float>>(){
    @Override public int compare(    Map.Entry<String,Float> o1,    Map.Entry<String,Float> o2){
      return o1.getValue().compareTo(o2.getValue());
    }
  }
).addAll(map.entrySet()).toList()) {
    result.put(entry.getKey(),entry.getValue());
  }
  return result;
}

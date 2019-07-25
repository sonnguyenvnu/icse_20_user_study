private static int increment(HashMap<String,Integer> map,String trace,int minCount){
  Integer oldCount=map.get(trace);
  if (oldCount == null) {
    map.put(trace,1);
  }
 else {
    map.put(trace,oldCount + 1);
  }
  while (map.size() > MAX_ELEMENTS) {
    for (Iterator<Map.Entry<String,Integer>> ei=map.entrySet().iterator(); ei.hasNext(); ) {
      Map.Entry<String,Integer> e=ei.next();
      if (e.getValue() <= minCount) {
        ei.remove();
      }
    }
    if (map.size() > MAX_ELEMENTS) {
      minCount++;
    }
  }
  return minCount;
}

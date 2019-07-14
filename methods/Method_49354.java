public K getMaxObject(){
  K result=null;
  double count=Double.MIN_VALUE;
  for (  Map.Entry<K,Counter> entry : countMap.entrySet()) {
    if (entry.getValue().count >= count) {
      count=entry.getValue().count;
      result=entry.getKey();
    }
  }
  return result;
}

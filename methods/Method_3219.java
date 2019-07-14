/** 
 * ???key??????
 * @param key    ? ???????
 * @param vector ??
 * @param size   topN?
 * @return ?????, ??????, ?????, ????????
 */
private List<Map.Entry<K,Float>> nearest(K key,Vector vector,int size){
  MaxHeap<Map.Entry<K,Float>> maxHeap=new MaxHeap<Map.Entry<K,Float>>(size,new Comparator<Map.Entry<K,Float>>(){
    @Override public int compare(    Map.Entry<K,Float> o1,    Map.Entry<K,Float> o2){
      return o1.getValue().compareTo(o2.getValue());
    }
  }
);
  for (  Map.Entry<K,Vector> entry : storage.entrySet()) {
    if (entry.getKey().equals(key)) {
      continue;
    }
    maxHeap.add(new AbstractMap.SimpleEntry<K,Float>(entry.getKey(),entry.getValue().cosineForUnitVector(vector)));
  }
  return maxHeap.toList();
}

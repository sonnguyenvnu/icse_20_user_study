default CompareOp<K,V> _XXXXX_(CompareResult result,K key,V value){
  return getOpFactory().compareValue(result,key,value);
}
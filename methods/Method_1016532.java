private static <K1,K2,V>SetMultimap<K2,V> get(Map<K1,SetMultimap<K2,V>> map,K1 key){
  SetMultimap<K2,V> result=map.get(key);
  if (result == null) {
    result=HashMultimap.create();
    map.put(key,result);
  }
  return result;
}

private <K,V>Map<K,V> sort(Map<K,V> self){
  return new TreeMap<>(self);
}

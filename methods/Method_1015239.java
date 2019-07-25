public static <K,V>SortedMap<K,V> from(java.util.Map<K,V> m){
  SortedMap<K,V> result=new SortedMap<K,V>().linear();
  m.entrySet().forEach(e -> result.put(e.getKey(),e.getValue()));
  return result.forked();
}

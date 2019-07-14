public static <K>Map<K,Integer> getIdMap(Iterable<K> elements){
  ImmutableMap.Builder<K,Integer> b=ImmutableMap.builder();
  int size=0;
  for (  K key : elements) {
    b.put(key,size++);
  }
  return b.build();
}

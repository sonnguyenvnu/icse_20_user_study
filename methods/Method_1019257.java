protected static <K,V>ImmutableMap<K,V> zip(Set<K> keys,Collection<V> values){
  ImmutableMap.Builder<K,V> builder=ImmutableMap.builder();
  Iterator<K> keyIterator=keys.iterator();
  Iterator<V> valueIterator=values.iterator();
  while (keyIterator.hasNext() && valueIterator.hasNext()) {
    builder.put(keyIterator.next(),valueIterator.next());
  }
  if (keyIterator.hasNext() || valueIterator.hasNext()) {
    throw new AssertionError();
  }
  return builder.build();
}

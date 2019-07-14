private HttpMultiMap<V> _set(final Iterable<Map.Entry<String,V>> map){
  clear();
  for (  Map.Entry<String,V> entry : map) {
    add(entry.getKey(),entry.getValue());
  }
  return this;
}

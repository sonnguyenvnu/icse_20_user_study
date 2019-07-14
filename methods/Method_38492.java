public HttpMultiMap<V> addAll(final HttpMultiMap<V> map){
  for (  Map.Entry<String,V> entry : map.entries()) {
    add(entry.getKey(),entry.getValue());
  }
  return this;
}

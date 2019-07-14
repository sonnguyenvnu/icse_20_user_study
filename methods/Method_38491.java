public HttpMultiMap<V> addAll(final String name,final Iterable<V> values){
  int h=hash(name);
  int i=index(h);
  for (  V value : values) {
    _add(h,i,name,value);
  }
  return this;
}

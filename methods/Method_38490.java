public HttpMultiMap<V> setAll(final String name,final Iterable<V> values){
  int h=hash(name);
  int i=index(h);
  _remove(h,i,name);
  for (  V v : values) {
    _add(h,i,name,v);
  }
  return this;
}

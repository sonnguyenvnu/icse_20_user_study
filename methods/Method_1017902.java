public void add(K key,V value){
  List<V> values=cache.getIfPresent(key);
  if (values == null) {
    values=new ArrayList<V>();
    cache.put(key,values);
  }
  values.add(value);
}

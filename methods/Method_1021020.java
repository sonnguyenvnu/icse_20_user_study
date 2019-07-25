@Override public Collection<V> values(){
  Collection<V> values=Collections.emptyList();
  List keys=Jboot.getCache().getKeys(cacheName);
  if (!CollectionUtils.isEmpty(keys)) {
    values=new ArrayList<>(keys.size());
    for (    Object key : keys) {
      V value=Jboot.getCache().get(cacheName,key);
      if (value != null) {
        values.add(value);
      }
    }
  }
  return values;
}

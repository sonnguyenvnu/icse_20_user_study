protected boolean add(K key,V val,boolean if_absent){
  boolean added=false;
  if (key != null && val != null) {
    Entry<V> entry=new Entry<>(val);
    added=if_absent ? map.putIfAbsent(key,entry) == null : map.put(key,entry) == null;
    if (added)     checkMaxSizeExceeded();
  }
  return added;
}

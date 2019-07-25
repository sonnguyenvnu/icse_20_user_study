public void remove(K key,boolean force){
  if (key == null)   return;
  if (force)   map.remove(key);
 else {
    Entry<V> entry=map.get(key);
    if (entry != null)     entry.setRemovable(true);
  }
  checkMaxSizeExceeded();
}

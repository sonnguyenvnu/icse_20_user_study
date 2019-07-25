public Map<K,V> contents(boolean skip_removed_values){
  Map<K,V> retval=new HashMap<>();
  for (  Map.Entry<K,Entry<V>> entry : map.entrySet()) {
    Entry<V> val=entry.getValue();
    if (val.isRemovable() && skip_removed_values)     continue;
    retval.put(entry.getKey(),entry.getValue().val);
  }
  return retval;
}

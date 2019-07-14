private void maybeNotifyExclusiveEntryRemoval(@Nullable ArrayList<Entry<K,V>> entries){
  if (entries != null) {
    for (    Entry<K,V> entry : entries) {
      maybeNotifyExclusiveEntryRemoval(entry);
    }
  }
}

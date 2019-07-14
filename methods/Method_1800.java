private static <K,V>void maybeNotifyExclusiveEntryRemoval(@Nullable Entry<K,V> entry){
  if (entry != null && entry.observer != null) {
    entry.observer.onExclusivityChanged(entry.key,false);
  }
}

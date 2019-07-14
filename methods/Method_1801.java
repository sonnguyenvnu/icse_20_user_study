private static <K,V>void maybeNotifyExclusiveEntryInsertion(@Nullable Entry<K,V> entry){
  if (entry != null && entry.observer != null) {
    entry.observer.onExclusivityChanged(entry.key,true);
  }
}

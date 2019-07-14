static <K,V>void connectAccessOrder(@Nonnull ReferenceEntry<K,V> previous,@Nonnull ReferenceEntry<K,V> next){
  previous.setNextInAccessQueue(next);
  next.setPreviousInAccessQueue(previous);
}

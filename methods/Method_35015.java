static <K,V>void connectWriteOrder(@Nonnull ReferenceEntry<K,V> previous,@Nonnull ReferenceEntry<K,V> next){
  previous.setNextInWriteQueue(next);
  next.setPreviousInWriteQueue(previous);
}

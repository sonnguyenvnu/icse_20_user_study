static <K,V>void nullifyAccessOrder(@Nonnull ReferenceEntry<K,V> nulled){
  ReferenceEntry<K,V> nullEntry=nullEntry();
  nulled.setNextInAccessQueue(nullEntry);
  nulled.setPreviousInAccessQueue(nullEntry);
}

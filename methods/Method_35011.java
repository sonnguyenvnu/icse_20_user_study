/** 
 * This method is a convenience for testing. Code should call  {@link Segment#copyEntry} directly.
 */
@Nullable ReferenceEntry<K,V> copyEntry(@Nonnull ReferenceEntry<K,V> original,ReferenceEntry<K,V> newNext){
  int hash=original.getHash();
  return segmentFor(hash).copyEntry(original,newNext);
}

/** 
 * This method is a convenience for testing. Code should call  {@link Segment#setValue} instead.
 */
@Nonnull ValueReference<K,V> newValueReference(@Nonnull ReferenceEntry<K,V> entry,@Nonnull V value,int weight){
  int hash=entry.getHash();
  return valueStrength.referenceValue(segmentFor(hash),entry,Preconditions.checkNotNull(value),weight);
}

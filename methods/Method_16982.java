/** 
 * Gets the ith element of given table (if nonnull) with volatile read semantics. Note: This is manually integrated into a few performance-sensitive methods to reduce call overhead.
 */
@SuppressWarnings("unchecked") static final <K,V>HashEntry<K,V> entryAt(HashEntry<K,V>[] tab,int i){
  return (tab == null) ? null : (HashEntry<K,V>)UNSAFE.getObjectVolatile(tab,((long)i << TSHIFT) + TBASE);
}

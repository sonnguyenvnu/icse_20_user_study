/** 
 * Get an unmodifiable iterator for the underlying items.
 * @see java.lang.Iterable#iterator()
 */
@Override public ChunkIterator iterator(){
  return new ChunkIterator(items);
}

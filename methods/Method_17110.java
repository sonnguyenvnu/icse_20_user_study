/** 
 * Returns an unmodifiable snapshot map ordered in write expiration order, either ascending or descending. Beware that obtaining the mappings is <em>NOT</em> a constant-time operation.
 * @param limit the maximum number of entries
 * @param transformer a function that unwraps the value
 * @param oldest the iteration order
 * @return an unmodifiable snapshot in a specified order
 */
@SuppressWarnings("GuardedByChecker") Map<K,V> expireAfterWriteOrder(int limit,Function<V,V> transformer,boolean oldest){
  Supplier<Iterator<Node<K,V>>> iteratorSupplier=() -> oldest ? writeOrderDeque().iterator() : writeOrderDeque().descendingIterator();
  return fixedSnapshot(iteratorSupplier,limit,transformer);
}

/** 
 * Returns an unmodifiable snapshot map ordered in access expiration order, either ascending or descending. Beware that obtaining the mappings is <em>NOT</em> a constant-time operation.
 * @param limit the maximum number of entries
 * @param transformer a function that unwraps the value
 * @param oldest the iteration order
 * @return an unmodifiable snapshot in a specified order
 */
@SuppressWarnings("GuardedByChecker") Map<K,V> expireAfterAccessOrder(int limit,Function<V,V> transformer,boolean oldest){
  if (!evicts()) {
    Supplier<Iterator<Node<K,V>>> iteratorSupplier=() -> oldest ? accessOrderWindowDeque().iterator() : accessOrderWindowDeque().descendingIterator();
    return fixedSnapshot(iteratorSupplier,limit,transformer);
  }
  Supplier<Iterator<Node<K,V>>> iteratorSupplier=() -> {
    Comparator<Node<K,V>> comparator=Comparator.comparingLong(Node::getAccessTime);
    PeekingIterator<Node<K,V>> first, second, third;
    if (oldest) {
      first=accessOrderWindowDeque().iterator();
      second=accessOrderProbationDeque().iterator();
      third=accessOrderProtectedDeque().iterator();
    }
 else {
      comparator=comparator.reversed();
      first=accessOrderWindowDeque().descendingIterator();
      second=accessOrderProbationDeque().descendingIterator();
      third=accessOrderProtectedDeque().descendingIterator();
    }
    return PeekingIterator.comparing(PeekingIterator.comparing(first,second,comparator),third,comparator);
  }
;
  return fixedSnapshot(iteratorSupplier,limit,transformer);
}

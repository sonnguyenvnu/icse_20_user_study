/** 
 * Returns an unmodifiable snapshot map ordered in eviction order, either ascending or descending. Beware that obtaining the mappings is <em>NOT</em> a constant-time operation.
 * @param limit the maximum number of entries
 * @param transformer a function that unwraps the value
 * @param hottest the iteration order
 * @return an unmodifiable snapshot in a specified order
 */
@SuppressWarnings("GuardedByChecker") Map<K,V> evictionOrder(int limit,Function<V,V> transformer,boolean hottest){
  Supplier<Iterator<Node<K,V>>> iteratorSupplier=() -> {
    Comparator<Node<K,V>> comparator=Comparator.comparingInt(node -> {
      K key=node.getKey();
      return (key == null) ? 0 : frequencySketch().frequency(key);
    }
);
    if (hottest) {
      PeekingIterator<Node<K,V>> secondary=PeekingIterator.comparing(accessOrderProbationDeque().descendingIterator(),accessOrderWindowDeque().descendingIterator(),comparator);
      return PeekingIterator.concat(accessOrderProtectedDeque().descendingIterator(),secondary);
    }
 else {
      PeekingIterator<Node<K,V>> primary=PeekingIterator.comparing(accessOrderWindowDeque().iterator(),accessOrderProbationDeque().iterator(),comparator.reversed());
      return PeekingIterator.concat(primary,accessOrderProtectedDeque().iterator());
    }
  }
;
  return fixedSnapshot(iteratorSupplier,limit,transformer);
}

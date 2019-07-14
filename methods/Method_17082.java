/** 
 * Evicts entries from the window space into the main space while the window size exceeds a maximum.
 * @return the number of candidate entries evicted from the window space
 */
@GuardedBy("evictionLock") int evictFromWindow(){
  int candidates=0;
  Node<K,V> node=accessOrderWindowDeque().peek();
  while (windowWeightedSize() > windowMaximum()) {
    if (node == null) {
      break;
    }
    Node<K,V> next=node.getNextInAccessOrder();
    if (node.getWeight() != 0) {
      node.makeMainProbation();
      accessOrderWindowDeque().remove(node);
      accessOrderProbationDeque().add(node);
      candidates++;
      setWindowWeightedSize(windowWeightedSize() - node.getPolicyWeight());
    }
    node=next;
  }
  return candidates;
}

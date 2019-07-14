/** 
 * Promote the node from probation to protected on an access. 
 */
@GuardedBy("evictionLock") void reorderProbation(Node<K,V> node){
  if (!accessOrderProbationDeque().contains(node)) {
    return;
  }
 else   if (node.getPolicyWeight() > mainProtectedMaximum()) {
    return;
  }
  setMainProtectedWeightedSize(mainProtectedWeightedSize() + node.getPolicyWeight());
  accessOrderProbationDeque().remove(node);
  accessOrderProtectedDeque().add(node);
  node.makeMainProtected();
}

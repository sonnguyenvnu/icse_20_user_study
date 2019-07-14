/** 
 * Updates the node's location in the page replacement policy. 
 */
@GuardedBy("evictionLock") void onAccess(Node<K,V> node){
  if (evicts()) {
    K key=node.getKey();
    if (key == null) {
      return;
    }
    frequencySketch().increment(key);
    if (node.inWindow()) {
      reorder(accessOrderWindowDeque(),node);
    }
 else     if (node.inMainProbation()) {
      reorderProbation(node);
    }
 else {
      reorder(accessOrderProtectedDeque(),node);
    }
    setHitsInSample(hitsInSample() + 1);
  }
 else   if (expiresAfterAccess()) {
    reorder(accessOrderWindowDeque(),node);
  }
  if (expiresVariable()) {
    timerWheel().reschedule(node);
  }
}

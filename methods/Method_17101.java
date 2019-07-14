/** 
 * Atomically transitions the node to the <tt>dead</tt> state and decrements the <tt>weightedSize</tt>.
 * @param node the entry in the page replacement policy
 */
@GuardedBy("evictionLock") void makeDead(Node<K,V> node){
synchronized (node) {
    if (node.isDead()) {
      return;
    }
    if (evicts()) {
      if (node.inWindow()) {
        setWindowWeightedSize(windowWeightedSize() - node.getWeight());
      }
 else       if (node.inMainProtected()) {
        setMainProtectedWeightedSize(mainProtectedWeightedSize() - node.getWeight());
      }
      setWeightedSize(weightedSize() - node.getWeight());
    }
    node.die();
  }
}

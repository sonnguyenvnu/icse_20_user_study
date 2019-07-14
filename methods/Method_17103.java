@GuardedBy("evictionLock") @SuppressWarnings("GuardedByChecker") void removeNode(Node<K,V> node,long now){
  K key=node.getKey();
  @SuppressWarnings("unchecked") V[] value=(V[])new Object[1];
  RemovalCause[] cause=new RemovalCause[1];
  data.computeIfPresent(node.getKeyReference(),(k,n) -> {
    if (n != node) {
      return n;
    }
synchronized (n) {
      value[0]=n.getValue();
      if ((key == null) || (value[0] == null)) {
        cause[0]=RemovalCause.COLLECTED;
      }
 else       if (hasExpired(n,now)) {
        cause[0]=RemovalCause.EXPIRED;
      }
 else {
        cause[0]=RemovalCause.EXPLICIT;
      }
      if (key != null) {
        writer.delete(key,value[0],cause[0]);
      }
      makeDead(n);
      return null;
    }
  }
);
  if (node.inWindow() && (evicts() || expiresAfterAccess())) {
    accessOrderWindowDeque().remove(node);
  }
 else   if (evicts()) {
    if (node.inMainProbation()) {
      accessOrderProbationDeque().remove(node);
    }
 else {
      accessOrderProtectedDeque().remove(node);
    }
  }
  if (expiresAfterWrite()) {
    writeOrderDeque().remove(node);
  }
 else   if (expiresVariable()) {
    timerWheel().deschedule(node);
  }
  if ((cause[0] != null) && hasRemovalListener()) {
    notifyRemoval(key,value[0],cause[0]);
  }
}

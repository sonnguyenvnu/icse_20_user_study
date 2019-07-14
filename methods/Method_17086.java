/** 
 * Expires entries in an access-order queue. 
 */
@GuardedBy("evictionLock") void expireAfterAccessEntries(AccessOrderDeque<Node<K,V>> accessOrderDeque,long now){
  long duration=expiresAfterAccessNanos();
  for (; ; ) {
    Node<K,V> node=accessOrderDeque.peekFirst();
    if ((node == null) || ((now - node.getAccessTime()) < duration)) {
      return;
    }
    evictEntry(node,RemovalCause.EXPIRED,now);
  }
}

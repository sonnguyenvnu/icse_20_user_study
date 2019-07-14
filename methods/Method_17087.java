/** 
 * Expires entries on the write-order queue. 
 */
@GuardedBy("evictionLock") void expireAfterWriteEntries(long now){
  if (!expiresAfterWrite()) {
    return;
  }
  long duration=expiresAfterWriteNanos();
  for (; ; ) {
    final Node<K,V> node=writeOrderDeque().peekFirst();
    if ((node == null) || ((now - node.getWriteTime()) < duration)) {
      break;
    }
    evictEntry(node,RemovalCause.EXPIRED,now);
  }
}

/** 
 * Drains the weak / soft value references queue. 
 */
@GuardedBy("evictionLock") void drainValueReferences(){
  if (!collectValues()) {
    return;
  }
  Reference<? extends V> valueRef;
  while ((valueRef=valueReferenceQueue().poll()) != null) {
    @SuppressWarnings("unchecked") InternalReference<V> ref=(InternalReference<V>)valueRef;
    Node<K,V> node=data.get(ref.getKeyReference());
    if ((node != null) && (valueRef == node.getValueReference())) {
      evictEntry(node,RemovalCause.COLLECTED,0L);
    }
  }
}

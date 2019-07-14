/** 
 * Returns if the entry has expired. 
 */
@SuppressWarnings("ShortCircuitBoolean") boolean hasExpired(Node<K,V> node,long now){
  return (expiresAfterAccess() && (now - node.getAccessTime() >= expiresAfterAccessNanos())) | (expiresAfterWrite() && (now - node.getWriteTime() >= expiresAfterWriteNanos())) | (expiresVariable() && (now - node.getVariableTime() >= 0));
}

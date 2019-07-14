/** 
 * Returns the access time for the entry after a read.
 * @param node the entry in the page replacement policy
 * @param key the key of the entry that was read
 * @param value the value of the entry that was read
 * @param expiry the calculator for the expiration time
 * @param now the current time, in nanoseconds
 * @return the expiration time
 */
long expireAfterRead(Node<K,V> node,@Nullable K key,@Nullable V value,Expiry<K,V> expiry,long now){
  if (expiresVariable() && (key != null) && (value != null)) {
    long currentDuration=Math.max(1,node.getVariableTime() - now);
    long duration=expiry.expireAfterRead(key,value,now,currentDuration);
    return isAsync ? (now + duration) : (now + Math.min(duration,MAXIMUM_EXPIRY));
  }
  return 0L;
}

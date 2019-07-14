/** 
 * Returns the cache entry listener registrations. 
 */
public Set<Registration<K,V>> registrations(){
  return Collections.unmodifiableSet(dispatchQueues.keySet());
}

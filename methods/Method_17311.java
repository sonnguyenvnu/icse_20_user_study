/** 
 * Publishes a update event for the entry to all of the interested listeners.
 * @param cache the cache where the entry was updated
 * @param key the entry's key
 * @param oldValue the entry's old value
 * @param newValue the entry's new value
 */
public void publishUpdated(Cache<K,V> cache,K key,V oldValue,V newValue){
  publish(cache,EventType.UPDATED,key,true,oldValue,newValue,false);
}

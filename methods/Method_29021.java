/** 
 * Increments by one the value currently associated with  {@code key}, and returns the new value.
 */
public long incrementAndGet(K key){
  return addAndGet(key,1);
}

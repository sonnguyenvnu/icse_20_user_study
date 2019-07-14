/** 
 * Decrements by one the value currently associated with  {@code key}, and returns the old value.
 */
public long getAndDecrement(K key){
  return getAndAdd(key,-1);
}

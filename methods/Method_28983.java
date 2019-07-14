/** 
 * If  {@code (key, expectedOldValue)} is currently in the map, this method replaces{@code expectedOldValue} with {@code newValue} and returns true; otherwise, this methodreturns false. <p/> <p>If  {@code expectedOldValue} is zero, this method will succeed if {@code (key, zero)}is currently in the map, or if  {@code key} is not in the map at all.
 */
boolean replace(K key,long expectedOldValue,long newValue){
  if (expectedOldValue == 0L) {
    return putIfAbsent(key,newValue) == 0L;
  }
 else {
    AtomicLong atomic=map.get(key);
    return (atomic == null) ? false : atomic.compareAndSet(expectedOldValue,newValue);
  }
}

/** 
 * Adds  {@code delta} to the value currently associated with {@code key}, and returns the old value.
 */
public long getAndAdd(K key,long delta){
  outer:   for (; ; ) {
    AtomicLong atomic=map.get(key);
    if (atomic == null) {
      atomic=map.putIfAbsent(key,new AtomicLong(delta));
      if (atomic == null) {
        return 0L;
      }
    }
    for (; ; ) {
      long oldValue=atomic.get();
      if (oldValue == 0L) {
        if (map.replace(key,atomic,new AtomicLong(delta))) {
          return 0L;
        }
        continue outer;
      }
      long newValue=oldValue + delta;
      if (atomic.compareAndSet(oldValue,newValue)) {
        return oldValue;
      }
    }
  }
}

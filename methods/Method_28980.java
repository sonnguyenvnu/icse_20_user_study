/** 
 * Adds  {@code delta} to the value currently associated with {@code key}, and returns the new value.
 */
public long addAndGet(K key,long delta){
  outer:   for (; ; ) {
    AtomicLong atomic=map.get(key);
    if (atomic == null) {
      atomic=map.putIfAbsent(key,new AtomicLong(delta));
      if (atomic == null) {
        return delta;
      }
    }
    for (; ; ) {
      long oldValue=atomic.get();
      if (oldValue == 0L) {
        if (map.replace(key,atomic,new AtomicLong(delta))) {
          return delta;
        }
        continue outer;
      }
      long newValue=oldValue + delta;
      if (atomic.compareAndSet(oldValue,newValue)) {
        return newValue;
      }
    }
  }
}

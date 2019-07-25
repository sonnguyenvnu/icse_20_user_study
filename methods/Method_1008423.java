/** 
 * Acquires a lock for the given key. The key is compared by it's equals method not by object identity. The lock can be acquired by the same thread multiple times. The lock is released by closing the returned  {@link Releasable}.
 */
public Releasable acquire(T key){
  while (true) {
    KeyLock perNodeLock=map.get(key);
    if (perNodeLock == null) {
      ReleasableLock newLock=tryCreateNewLock(key);
      if (newLock != null) {
        return newLock;
      }
    }
 else {
      assert perNodeLock != null;
      int i=perNodeLock.count.get();
      if (i > 0 && perNodeLock.count.compareAndSet(i,i + 1)) {
        perNodeLock.lock();
        return new ReleasableLock(key,perNodeLock);
      }
    }
  }
}

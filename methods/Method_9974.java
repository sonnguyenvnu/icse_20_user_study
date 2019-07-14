/** 
 * Unlock.
 * @param lockName the specified lock name
 */
public synchronized static void unlock(final String lockName){
  JSONObject lock=LOCKS.get(lockName);
  if (null == lock) {
    lock=new JSONObject();
  }
  lock.put(Common.LOCK,false);
  LOCKS.put(lockName,lock);
}

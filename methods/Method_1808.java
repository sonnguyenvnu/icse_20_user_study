/** 
 * Gets the number of the exclusively owned items. 
 */
public synchronized int getEvictionQueueCount(){
  return mExclusiveEntries.getCount();
}

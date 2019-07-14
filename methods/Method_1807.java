/** 
 * Gets the number of the cached items that are used by at least one client. 
 */
public synchronized int getInUseCount(){
  return mCachedEntries.getCount() - mExclusiveEntries.getCount();
}

/** 
 * Bump up the reference count for the shared reference Note: The reference must be valid (aka not null) at this point
 */
public synchronized void addReference(){
  ensureValid();
  mRefCount++;
}

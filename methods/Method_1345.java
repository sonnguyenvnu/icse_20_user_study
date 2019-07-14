/** 
 * Checks if this shared-reference is valid i.e. its reference count is greater than zero.
 * @return true if shared reference is valid
 */
public synchronized boolean isValid(){
  return mRefCount > 0;
}

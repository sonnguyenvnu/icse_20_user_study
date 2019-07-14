/** 
 * Bump up the reference count for the shared reference if the shared-reference is valid. 
 */
public synchronized boolean addReferenceIfValid(){
  if (isValid()) {
    addReference();
    return true;
  }
  return false;
}

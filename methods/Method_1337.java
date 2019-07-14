/** 
 * Checks if this closable-reference is valid i.e. is not closed.
 * @return true if the closeable reference is valid
 */
public synchronized boolean isValid(){
  return !mIsClosed;
}

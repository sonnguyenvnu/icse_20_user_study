/** 
 * Checks that the cache is not closed. 
 */
protected final void requireNotClosed(){
  if (isClosed()) {
    throw new IllegalStateException();
  }
}

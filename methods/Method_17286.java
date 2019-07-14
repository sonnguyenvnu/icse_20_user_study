/** 
 * Checks that the cache manager is not closed. 
 */
private void requireNotClosed(){
  if (isClosed()) {
    throw new IllegalStateException();
  }
}

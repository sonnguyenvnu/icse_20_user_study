/** 
 * Closes the condition.
 * @return True if the condition variable was closed. False if it was already closed.
 */
public synchronized boolean close(){
  boolean wasOpen=isOpen;
  isOpen=false;
  return wasOpen;
}

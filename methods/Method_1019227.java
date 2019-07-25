/** 
 * Puts an empty entry to queue to denote termination
 */
public void close() throws InterruptedException {
  isWriteDone.set(true);
}

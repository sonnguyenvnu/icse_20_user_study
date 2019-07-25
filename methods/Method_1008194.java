/** 
 * Flush pending delete or index requests.
 */
public synchronized void flush(){
  ensureOpen();
  if (bulkRequest.numberOfActions() > 0) {
    execute();
  }
}

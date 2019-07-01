/** 
 * Shares the SPDY connection with the pool. Callers to this method may continue to use  {@code connection}.
 */
public void _XXXXX_(Connection connection){
  executorService.submit(connectionsCleanupCallable);
  if (!connection.isSpdy()) {
    return;
  }
  if (connection.isAlive()) {
synchronized (this) {
      connections.addFirst(connection);
    }
  }
}
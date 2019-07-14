/** 
 * Returns connection stats.
 */
public synchronized SizeSnapshot getConnectionsCount(){
  return new SizeSnapshot(availableConnections.size(),busyConnections.size());
}

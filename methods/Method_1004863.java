public synchronized void heartbeat(long heartbeatValue) throws Exception {
  try (Connection c=connectionPool.getConnection()){
    heartbeat(c,heartbeatValue);
  }
 }

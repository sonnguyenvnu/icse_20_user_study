@Override public void destroy(Connection<TTransport,InetSocketAddress> connection){
  activeConnectionsWriteLock.lock();
  try {
    boolean wasActiveConnection=activeConnections.remove(connection);
    Preconditions.checkArgument(wasActiveConnection,"connection %s not created by this factory",connection);
    lastActiveConnectionsSize=activeConnections.size();
  }
  finally {
    activeConnectionsWriteLock.unlock();
  }
  connection.close();
}

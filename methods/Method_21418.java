public int removeAbandoned(){
  int removeCount=0;
  long currrentNanos=System.nanoTime();
  List<DruidPooledConnection> abandonedList=new ArrayList<DruidPooledConnection>();
  activeConnectionLock.lock();
  try {
    Iterator<DruidPooledConnection> iter=activeConnections.keySet().iterator();
    for (; iter.hasNext(); ) {
      DruidPooledConnection pooledConnection=iter.next();
      if (pooledConnection.isRunning()) {
        continue;
      }
      long timeMillis=(currrentNanos - pooledConnection.getConnectedTimeNano()) / (1000 * 1000);
      if (timeMillis >= removeAbandonedTimeoutMillis) {
        iter.remove();
        pooledConnection.setTraceEnable(false);
        abandonedList.add(pooledConnection);
      }
    }
  }
  finally {
    activeConnectionLock.unlock();
  }
  if (abandonedList.size() > 0) {
    for (    DruidPooledConnection pooledConnection : abandonedList) {
      final ReentrantLock lock=pooledConnection.lock;
      lock.lock();
      try {
        if (pooledConnection.isDisable()) {
          continue;
        }
      }
  finally {
        lock.unlock();
      }
      JdbcUtils.close(pooledConnection);
      pooledConnection.abandond();
      removeAbandonedCount++;
      removeCount++;
      if (isLogAbandoned()) {
        StringBuilder buf=new StringBuilder();
        buf.append("abandon connection, owner thread: ");
        buf.append(pooledConnection.getOwnerThread().getName());
        buf.append(", connected at : ");
        buf.append(pooledConnection.getConnectedTimeMillis());
        buf.append(", open stackTrace\n");
        StackTraceElement[] trace=pooledConnection.getConnectStackTrace();
        for (int i=0; i < trace.length; i++) {
          buf.append("\tat ");
          buf.append(trace[i].toString());
          buf.append("\n");
        }
        buf.append("ownerThread current state is " + pooledConnection.getOwnerThread().getState() + ", current stackTrace\n");
        trace=pooledConnection.getOwnerThread().getStackTrace();
        for (int i=0; i < trace.length; i++) {
          buf.append("\tat ");
          buf.append(trace[i].toString());
          buf.append("\n");
        }
        LOG.error(buf.toString());
      }
    }
  }
  return removeCount;
}

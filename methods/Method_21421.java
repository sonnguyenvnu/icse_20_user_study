public void shrink(boolean checkTime,boolean keepAlive){
  try {
    lock.lockInterruptibly();
  }
 catch (  InterruptedException e) {
    return;
  }
  int evictCount=0;
  int keepAliveCount=0;
  try {
    if (!inited) {
      return;
    }
    final int checkCount=poolingCount - minIdle;
    final long currentTimeMillis=System.currentTimeMillis();
    for (int i=0; i < poolingCount; ++i) {
      DruidConnectionHolder connection=connections[i];
      if (checkTime) {
        if (phyTimeoutMillis > 0) {
          long phyConnectTimeMillis=currentTimeMillis - connection.connectTimeMillis;
          if (phyConnectTimeMillis > phyTimeoutMillis) {
            evictConnections[evictCount++]=connection;
            continue;
          }
        }
        long idleMillis=currentTimeMillis - connection.lastActiveTimeMillis;
        if (idleMillis < minEvictableIdleTimeMillis && idleMillis < keepAliveBetweenTimeMillis) {
          break;
        }
        if (idleMillis >= minEvictableIdleTimeMillis) {
          if (checkTime && i < checkCount) {
            evictConnections[evictCount++]=connection;
            continue;
          }
 else           if (idleMillis > maxEvictableIdleTimeMillis) {
            evictConnections[evictCount++]=connection;
            continue;
          }
        }
        if (keepAlive && idleMillis >= keepAliveBetweenTimeMillis) {
          keepAliveConnections[keepAliveCount++]=connection;
        }
      }
 else {
        if (i < checkCount) {
          evictConnections[evictCount++]=connection;
        }
 else {
          break;
        }
      }
    }
    int removeCount=evictCount + keepAliveCount;
    if (removeCount > 0) {
      System.arraycopy(connections,removeCount,connections,0,poolingCount - removeCount);
      Arrays.fill(connections,poolingCount - removeCount,poolingCount,null);
      poolingCount-=removeCount;
    }
    keepAliveCheckCount+=keepAliveCount;
  }
  finally {
    lock.unlock();
  }
  if (evictCount > 0) {
    for (int i=0; i < evictCount; ++i) {
      DruidConnectionHolder item=evictConnections[i];
      Connection connection=item.getConnection();
      JdbcUtils.close(connection);
      destroyCountUpdater.incrementAndGet(this);
    }
    Arrays.fill(evictConnections,null);
  }
  if (keepAliveCount > 0) {
    for (int i=keepAliveCount - 1; i >= 0; --i) {
      DruidConnectionHolder holer=keepAliveConnections[i];
      Connection connection=holer.getConnection();
      holer.incrementKeepAliveCheckCount();
      boolean validate=false;
      try {
        this.validateConnection(connection);
        validate=true;
      }
 catch (      Throwable error) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("keepAliveErr",error);
        }
      }
      boolean discard=!validate;
      if (validate) {
        holer.lastKeepTimeMillis=System.currentTimeMillis();
        boolean putOk=put(holer);
        if (!putOk) {
          discard=true;
        }
      }
      if (discard) {
        try {
          connection.close();
        }
 catch (        Exception e) {
        }
        lock.lock();
        try {
          discardCount++;
          if (activeCount <= minIdle) {
            emptySignal();
          }
        }
  finally {
          lock.unlock();
        }
      }
    }
    this.getDataSourceStat().addKeepAliveCheckCount(keepAliveCount);
    Arrays.fill(keepAliveConnections,null);
  }
}

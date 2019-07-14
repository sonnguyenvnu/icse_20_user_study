/** 
 * ????
 */
protected void recycle(DruidPooledConnection pooledConnection) throws SQLException {
  final DruidConnectionHolder holder=pooledConnection.holder;
  if (holder == null) {
    LOG.warn("connectionHolder is null");
    return;
  }
  if (logDifferentThread && (!isAsyncCloseConnectionEnable()) && pooledConnection.ownerThread != Thread.currentThread()) {
    LOG.warn("get/close not same thread");
  }
  final Connection physicalConnection=holder.conn;
  if (pooledConnection.traceEnable) {
    Object oldInfo=null;
    activeConnectionLock.lock();
    try {
      if (pooledConnection.traceEnable) {
        oldInfo=activeConnections.remove(pooledConnection);
        pooledConnection.traceEnable=false;
      }
    }
  finally {
      activeConnectionLock.unlock();
    }
    if (oldInfo == null) {
      if (LOG.isWarnEnabled()) {
        LOG.warn("remove abandonded failed. activeConnections.size " + activeConnections.size());
      }
    }
  }
  final boolean isAutoCommit=holder.underlyingAutoCommit;
  final boolean isReadOnly=holder.underlyingReadOnly;
  final boolean testOnReturn=this.testOnReturn;
  try {
    if ((!isAutoCommit) && (!isReadOnly)) {
      pooledConnection.rollback();
    }
    boolean isSameThread=pooledConnection.ownerThread == Thread.currentThread();
    if (!isSameThread) {
      final ReentrantLock lock=pooledConnection.lock;
      lock.lock();
      try {
        holder.reset();
      }
  finally {
        lock.unlock();
      }
    }
 else {
      holder.reset();
    }
    if (holder.discard) {
      return;
    }
    if (phyMaxUseCount > 0 && holder.useCount >= phyMaxUseCount) {
      discardConnection(holder.conn);
      return;
    }
    if (physicalConnection.isClosed()) {
      lock.lock();
      try {
        activeCount--;
        closeCount++;
      }
  finally {
        lock.unlock();
      }
      return;
    }
    if (testOnReturn) {
      boolean validate=testConnectionInternal(holder,physicalConnection);
      if (!validate) {
        JdbcUtils.close(physicalConnection);
        destroyCountUpdater.incrementAndGet(this);
        lock.lock();
        try {
          activeCount--;
          closeCount++;
        }
  finally {
          lock.unlock();
        }
        return;
      }
    }
    if (!enable) {
      discardConnection(holder.conn);
      return;
    }
    boolean result;
    final long currentTimeMillis=System.currentTimeMillis();
    if (phyTimeoutMillis > 0) {
      long phyConnectTimeMillis=currentTimeMillis - holder.connectTimeMillis;
      if (phyConnectTimeMillis > phyTimeoutMillis) {
        discardConnection(holder.conn);
        return;
      }
    }
    lock.lock();
    try {
      activeCount--;
      closeCount++;
      result=putLast(holder,currentTimeMillis);
      recycleCount++;
    }
  finally {
      lock.unlock();
    }
    if (!result) {
      JdbcUtils.close(holder.conn);
      LOG.info("connection recyle failed.");
    }
  }
 catch (  Throwable e) {
    holder.clearStatementCache();
    if (!holder.discard) {
      this.discardConnection(physicalConnection);
      holder.discard=true;
    }
    LOG.error("recyle error",e);
    recycleErrorCountUpdater.incrementAndGet(this);
  }
}

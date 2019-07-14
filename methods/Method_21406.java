private DruidPooledConnection getConnectionInternal(long maxWait) throws SQLException {
  if (closed) {
    connectErrorCountUpdater.incrementAndGet(this);
    throw new DataSourceClosedException("dataSource already closed at " + new Date(closeTimeMillis));
  }
  if (!enable) {
    connectErrorCountUpdater.incrementAndGet(this);
    throw new DataSourceDisableException();
  }
  final long nanos=TimeUnit.MILLISECONDS.toNanos(maxWait);
  final int maxWaitThreadCount=this.maxWaitThreadCount;
  DruidConnectionHolder holder;
  for (boolean createDirect=false; ; ) {
    if (createDirect) {
      createStartNanosUpdater.set(this,System.nanoTime());
      if (creatingCountUpdater.compareAndSet(this,0,1)) {
        PhysicalConnectionInfo pyConnInfo=ElasticSearchDruidDataSource.this.createPhysicalConnection();
        holder=new DruidConnectionHolder(this,pyConnInfo);
        holder.lastActiveTimeMillis=System.currentTimeMillis();
        creatingCountUpdater.decrementAndGet(this);
        directCreateCountUpdater.incrementAndGet(this);
        if (LOG.isDebugEnabled()) {
          LOG.debug("conn-direct_create ");
        }
        boolean discard=false;
        lock.lock();
        try {
          if (activeCount < maxActive) {
            activeCount++;
            if (activeCount > activePeak) {
              activePeak=activeCount;
              activePeakTime=System.currentTimeMillis();
            }
            break;
          }
 else {
            discard=true;
          }
        }
  finally {
          lock.unlock();
        }
        if (discard) {
          JdbcUtils.close(pyConnInfo.getPhysicalConnection());
        }
      }
    }
    try {
      lock.lockInterruptibly();
    }
 catch (    InterruptedException e) {
      connectErrorCountUpdater.incrementAndGet(this);
      throw new SQLException("interrupt",e);
    }
    try {
      if (maxWaitThreadCount > 0 && notEmptyWaitThreadCount >= maxWaitThreadCount) {
        connectErrorCountUpdater.incrementAndGet(this);
        throw new SQLException("maxWaitThreadCount " + maxWaitThreadCount + ", current wait Thread count " + lock.getQueueLength());
      }
      if (onFatalError && onFatalErrorMaxActive > 0 && activeCount >= onFatalErrorMaxActive) {
        connectErrorCountUpdater.incrementAndGet(this);
        StringBuilder errorMsg=new StringBuilder();
        errorMsg.append("onFatalError, activeCount ").append(activeCount).append(", onFatalErrorMaxActive ").append(onFatalErrorMaxActive);
        if (lastFatalErrorTimeMillis > 0) {
          errorMsg.append(", time '").append(StringUtils.formatDateTime19(lastFatalErrorTimeMillis,TimeZone.getDefault())).append("'");
        }
        if (lastFatalErrorSql != null) {
          errorMsg.append(", sql \n").append(lastFatalErrorSql);
        }
        throw new SQLException(errorMsg.toString(),lastFatalError);
      }
      connectCount++;
      if (createScheduler != null && poolingCount == 0 && activeCount < maxActive && creatingCountUpdater.get(this) == 0 && createScheduler instanceof ScheduledThreadPoolExecutor) {
        ScheduledThreadPoolExecutor executor=(ScheduledThreadPoolExecutor)createScheduler;
        if (executor.getQueue().size() > 0) {
          createDirect=true;
          continue;
        }
      }
      if (maxWait > 0) {
        holder=pollLast(nanos);
      }
 else {
        holder=takeLast();
      }
      if (holder != null) {
        activeCount++;
        if (activeCount > activePeak) {
          activePeak=activeCount;
          activePeakTime=System.currentTimeMillis();
        }
      }
    }
 catch (    InterruptedException e) {
      connectErrorCountUpdater.incrementAndGet(this);
      throw new SQLException(e.getMessage(),e);
    }
catch (    SQLException e) {
      connectErrorCountUpdater.incrementAndGet(this);
      throw e;
    }
 finally {
      lock.unlock();
    }
    break;
  }
  if (holder == null) {
    long waitNanos=waitNanosLocal.get();
    StringBuilder buf=new StringBuilder(128);
    buf.append("wait millis ").append(waitNanos / (1000 * 1000)).append(", active ").append(activeCount).append(", maxActive ").append(maxActive).append(", creating ").append(creatingCount);
    if (creatingCount > 0 && createStartNanos > 0) {
      long createElapseMillis=(System.nanoTime() - createStartNanos) / (1000 * 1000);
      if (createElapseMillis > 0) {
        buf.append(", createElapseMillis ").append(createElapseMillis);
      }
    }
    if (createErrorCount > 0) {
      buf.append(", createErrorCount ").append(createErrorCount);
    }
    List<JdbcSqlStatValue> sqlList=this.getDataSourceStat().getRuningSqlList();
    for (int i=0; i < sqlList.size(); ++i) {
      if (i != 0) {
        buf.append('\n');
      }
 else {
        buf.append(", ");
      }
      JdbcSqlStatValue sql=sqlList.get(i);
      buf.append("runningSqlCount ").append(sql.getRunningCount());
      buf.append(" : ");
      buf.append(sql.getSql());
    }
    String errorMessage=buf.toString();
    if (this.createError != null) {
      throw new GetConnectionTimeoutException(errorMessage,createError);
    }
 else {
      throw new GetConnectionTimeoutException(errorMessage);
    }
  }
  holder.incrementUseCount();
  DruidPooledConnection poolalbeConnection=new ElasticSearchDruidPooledConnection(holder);
  return poolalbeConnection;
}

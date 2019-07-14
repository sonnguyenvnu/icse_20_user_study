public Map<String,Object> getStatDataForMBean(){
  try {
    Map<String,Object> map=new HashMap<String,Object>();
    map.put("Name",this.getName());
    map.put("URL",this.getUrl());
    map.put("CreateCount",this.getCreateCount());
    map.put("DestroyCount",this.getDestroyCount());
    map.put("ConnectCount",this.getConnectCount());
    map.put("CloseCount",this.getCloseCount());
    map.put("ActiveCount",this.getActiveCount());
    map.put("PoolingCount",this.getPoolingCount());
    map.put("LockQueueLength",this.getLockQueueLength());
    map.put("WaitThreadCount",this.getNotEmptyWaitThreadPeak());
    map.put("InitialSize",this.getInitialSize());
    map.put("MaxActive",this.getMaxActive());
    map.put("MinIdle",this.getMinIdle());
    map.put("PoolPreparedStatements",this.isPoolPreparedStatements());
    map.put("TestOnBorrow",this.isTestOnBorrow());
    map.put("TestOnReturn",this.isTestOnReturn());
    map.put("MinEvictableIdleTimeMillis",this.minEvictableIdleTimeMillis);
    map.put("ConnectErrorCount",this.getConnectErrorCount());
    map.put("CreateTimespanMillis",this.getCreateTimespanMillis());
    map.put("DbType",this.dbType);
    map.put("ValidationQuery",this.getValidationQuery());
    map.put("ValidationQueryTimeout",this.getValidationQueryTimeout());
    map.put("DriverClassName",this.getDriverClassName());
    map.put("Username",this.getUsername());
    map.put("RemoveAbandonedCount",this.getRemoveAbandonedCount());
    map.put("NotEmptyWaitCount",this.getNotEmptyWaitCount());
    map.put("NotEmptyWaitNanos",this.getNotEmptyWaitNanos());
    map.put("ErrorCount",this.getErrorCount());
    map.put("ReusePreparedStatementCount",this.getCachedPreparedStatementHitCount());
    map.put("StartTransactionCount",this.getStartTransactionCount());
    map.put("CommitCount",this.getCommitCount());
    map.put("RollbackCount",this.getRollbackCount());
    map.put("LastError",JMXUtils.getErrorCompositeData(this.getLastError()));
    map.put("LastCreateError",JMXUtils.getErrorCompositeData(this.getLastCreateError()));
    map.put("PreparedStatementCacheDeleteCount",this.getCachedPreparedStatementDeleteCount());
    map.put("PreparedStatementCacheAccessCount",this.getCachedPreparedStatementAccessCount());
    map.put("PreparedStatementCacheMissCount",this.getCachedPreparedStatementMissCount());
    map.put("PreparedStatementCacheHitCount",this.getCachedPreparedStatementHitCount());
    map.put("PreparedStatementCacheCurrentCount",this.getCachedPreparedStatementCount());
    map.put("Version",this.getVersion());
    map.put("LastErrorTime",this.getLastErrorTime());
    map.put("LastCreateErrorTime",this.getLastCreateErrorTime());
    map.put("CreateErrorCount",this.getCreateErrorCount());
    map.put("DiscardCount",this.getDiscardCount());
    map.put("ExecuteQueryCount",this.getExecuteQueryCount());
    map.put("ExecuteUpdateCount",this.getExecuteUpdateCount());
    return map;
  }
 catch (  JMException ex) {
    throw new IllegalStateException("getStatData error",ex);
  }
}

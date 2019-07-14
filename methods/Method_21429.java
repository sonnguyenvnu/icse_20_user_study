public List<Map<String,Object>> getPoolingConnectionInfo(){
  List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
  lock.lock();
  try {
    for (int i=0; i < poolingCount; ++i) {
      DruidConnectionHolder connHolder=connections[i];
      Connection conn=connHolder.getConnection();
      Map<String,Object> map=new LinkedHashMap<String,Object>();
      map.put("id",System.identityHashCode(conn));
      map.put("connectionId",connHolder.getConnectionId());
      map.put("useCount",connHolder.getUseCount());
      if (connHolder.lastActiveTimeMillis > 0) {
        map.put("lastActiveTime",new Date(connHolder.lastActiveTimeMillis));
      }
      if (connHolder.lastKeepTimeMillis > 0) {
        map.put("lastKeepTimeMillis",new Date(connHolder.lastKeepTimeMillis));
      }
      map.put("connectTime",new Date(connHolder.getTimeMillis()));
      map.put("holdability",connHolder.getUnderlyingHoldability());
      map.put("transactionIsolation",connHolder.getUnderlyingTransactionIsolation());
      map.put("autoCommit",connHolder.underlyingAutoCommit);
      map.put("readoOnly",connHolder.isUnderlyingReadOnly());
      if (connHolder.isPoolPreparedStatements()) {
        List<Map<String,Object>> stmtCache=new ArrayList<Map<String,Object>>();
        PreparedStatementPool stmtPool=connHolder.getStatementPool();
        for (        PreparedStatementHolder stmtHolder : stmtPool.getMap().values()) {
          Map<String,Object> stmtInfo=new LinkedHashMap<String,Object>();
          stmtInfo.put("sql",stmtHolder.key.getSql());
          stmtInfo.put("defaultRowPrefetch",stmtHolder.getDefaultRowPrefetch());
          stmtInfo.put("rowPrefetch",stmtHolder.getRowPrefetch());
          stmtInfo.put("hitCount",stmtHolder.getHitCount());
          stmtCache.add(stmtInfo);
        }
        map.put("pscache",stmtCache);
      }
      map.put("keepAliveCheckCount",connHolder.getKeepAliveCheckCount());
      list.add(map);
    }
  }
  finally {
    lock.unlock();
  }
  return list;
}

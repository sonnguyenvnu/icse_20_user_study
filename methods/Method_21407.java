public void handleConnectionException(DruidPooledConnection pooledConnection,Throwable t,String sql) throws SQLException {
  final DruidConnectionHolder holder=pooledConnection.getConnectionHolder();
  errorCountUpdater.incrementAndGet(this);
  lastError=t;
  lastErrorTimeMillis=System.currentTimeMillis();
  if (t instanceof SQLException) {
    SQLException sqlEx=(SQLException)t;
    ConnectionEvent event=new ConnectionEvent(pooledConnection,sqlEx);
    for (    ConnectionEventListener eventListener : holder.getConnectionEventListeners()) {
      eventListener.connectionErrorOccurred(event);
    }
    if (exceptionSorter != null && exceptionSorter.isExceptionFatal(sqlEx)) {
      handleFatalError(pooledConnection,sqlEx,sql);
    }
    throw sqlEx;
  }
 else {
    throw new SQLException("Error",t);
  }
}

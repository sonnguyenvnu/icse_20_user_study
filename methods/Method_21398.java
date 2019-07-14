@Override public PhysicalConnectionInfo createPhysicalConnection() throws SQLException {
  String url=this.getUrl();
  Properties connectProperties=getConnectProperties();
  Connection conn=null;
  long connectStartNanos=System.nanoTime();
  long connectedNanos, initedNanos, validatedNanos;
  Map<String,Object> variables=initVariants ? new HashMap<String,Object>() : null;
  Map<String,Object> globalVariables=initGlobalVariants ? new HashMap<String,Object>() : null;
  createStartNanosUpdater.set(this,connectStartNanos);
  creatingCountUpdater.incrementAndGet(this);
  try {
    conn=createPhysicalConnection(url,connectProperties);
    connectedNanos=System.nanoTime();
    if (conn == null) {
      throw new SQLException("connect error, url " + url + ", driverClass " + this.driverClass);
    }
    initPhysicalConnection(conn,variables,globalVariables);
    initedNanos=System.nanoTime();
    validateConnection(conn);
    validatedNanos=System.nanoTime();
    setFailContinuous(false);
    setCreateError(null);
  }
 catch (  SQLException ex) {
    setCreateError(ex);
    JdbcUtils.close(conn);
    throw ex;
  }
catch (  RuntimeException ex) {
    setCreateError(ex);
    JdbcUtils.close(conn);
    throw ex;
  }
catch (  Error ex) {
    createErrorCountUpdater.incrementAndGet(this);
    setCreateError(ex);
    JdbcUtils.close(conn);
    throw ex;
  }
 finally {
    long nano=System.nanoTime() - connectStartNanos;
    createTimespan+=nano;
    creatingCountUpdater.decrementAndGet(this);
  }
  return new PhysicalConnectionInfo(conn,connectStartNanos,connectedNanos,initedNanos,validatedNanos,variables,globalVariables);
}

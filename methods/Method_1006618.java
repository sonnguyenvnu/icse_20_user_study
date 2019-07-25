/** 
 * @see com.alipay.remoting.ConnectionManager#remove(java.lang.String)
 */
@Override public void remove(String poolKey){
  if (StringUtils.isBlank(poolKey)) {
    return;
  }
  RunStateRecordedFutureTask<ConnectionPool> task=this.connTasks.remove(poolKey);
  if (null != task) {
    ConnectionPool pool=this.getConnectionPool(task);
    if (null != pool) {
      pool.removeAllAndTryClose();
      logger.warn("Remove and close all connections in ConnectionPool of poolKey={}",poolKey);
    }
  }
}

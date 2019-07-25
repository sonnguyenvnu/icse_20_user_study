/** 
 * @see com.alipay.remoting.ConnectionManager#count(java.lang.String)
 */
@Override public int count(String poolKey){
  if (StringUtils.isBlank(poolKey)) {
    return 0;
  }
  ConnectionPool pool=this.getConnectionPool(this.connTasks.get(poolKey));
  if (null != pool) {
    return pool.size();
  }
 else {
    return 0;
  }
}

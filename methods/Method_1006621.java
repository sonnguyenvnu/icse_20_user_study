/** 
 * in case of cache pollution and connection leak, to do schedule scan
 */
@Override public void scan(){
  if (null != this.connTasks && !this.connTasks.isEmpty()) {
    Iterator<String> iter=this.connTasks.keySet().iterator();
    while (iter.hasNext()) {
      String poolKey=iter.next();
      ConnectionPool pool=this.getConnectionPool(this.connTasks.get(poolKey));
      if (null != pool) {
        pool.scan();
        if (pool.isEmpty()) {
          if ((System.currentTimeMillis() - pool.getLastAccessTimestamp()) > Constants.DEFAULT_EXPIRE_TIME) {
            iter.remove();
            logger.warn("Remove expired pool task of poolKey {} which is empty.",poolKey);
          }
        }
      }
    }
  }
}

/** 
 * ???????????????
 * @param realConnection
 */
public void discardConnection(Connection realConnection){
  JdbcUtils.close(realConnection);
  lock.lock();
  try {
    activeCount--;
    discardCount++;
    if (activeCount <= minIdle) {
      emptySignal();
    }
  }
  finally {
    lock.unlock();
  }
}

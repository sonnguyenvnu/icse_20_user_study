protected boolean put(PhysicalConnectionInfo physicalConnectionInfo){
  DruidConnectionHolder holder=null;
  try {
    holder=new DruidConnectionHolder(ElasticSearchDruidDataSource.this,physicalConnectionInfo);
  }
 catch (  SQLException ex) {
    lock.lock();
    try {
      if (createScheduler != null) {
        createTaskCount--;
      }
    }
  finally {
      lock.unlock();
    }
    LOG.error("create connection holder error",ex);
    return false;
  }
  return put(holder);
}

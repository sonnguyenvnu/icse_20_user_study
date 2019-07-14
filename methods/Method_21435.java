@Override public int fill(int toCount) throws SQLException {
  if (closed) {
    throw new DataSourceClosedException("dataSource already closed at " + new Date(closeTimeMillis));
  }
  if (toCount < 0) {
    throw new IllegalArgumentException("toCount can't not be less than zero");
  }
  init();
  if (toCount > this.maxActive) {
    toCount=this.maxActive;
  }
  int fillCount=0;
  for (; ; ) {
    try {
      lock.lockInterruptibly();
    }
 catch (    InterruptedException e) {
      connectErrorCountUpdater.incrementAndGet(this);
      throw new SQLException("interrupt",e);
    }
    boolean fillable=this.isFillable(toCount);
    lock.unlock();
    if (!fillable) {
      break;
    }
    DruidConnectionHolder holder;
    try {
      PhysicalConnectionInfo pyConnInfo=createPhysicalConnection();
      holder=new DruidConnectionHolder(this,pyConnInfo);
    }
 catch (    SQLException e) {
      LOG.error("fill connection error, url: " + this.jdbcUrl,e);
      connectErrorCountUpdater.incrementAndGet(this);
      throw e;
    }
    try {
      lock.lockInterruptibly();
    }
 catch (    InterruptedException e) {
      connectErrorCountUpdater.incrementAndGet(this);
      throw new SQLException("interrupt",e);
    }
    try {
      if (!this.isFillable(toCount)) {
        JdbcUtils.close(holder.getConnection());
        LOG.info("fill connections skip.");
        break;
      }
      this.putLast(holder,System.currentTimeMillis());
      fillCount++;
    }
  finally {
      lock.unlock();
    }
  }
  if (LOG.isInfoEnabled()) {
    LOG.info("fill " + fillCount + " connections");
  }
  return fillCount;
}

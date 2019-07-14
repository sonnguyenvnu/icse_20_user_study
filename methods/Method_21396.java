public void setMaxActive(int maxActive){
  if (this.maxActive == maxActive) {
    return;
  }
  if (maxActive == 0) {
    throw new IllegalArgumentException("maxActive can't not set zero");
  }
  if (!inited) {
    this.maxActive=maxActive;
    return;
  }
  if (maxActive < this.minIdle) {
    throw new IllegalArgumentException("maxActive less than minIdle, " + maxActive + " < " + this.minIdle);
  }
  if (LOG.isInfoEnabled()) {
    LOG.info("maxActive changed : " + this.maxActive + " -> " + maxActive);
  }
  lock.lock();
  try {
    int allCount=this.poolingCount + this.activeCount;
    if (maxActive > allCount) {
      this.connections=Arrays.copyOf(this.connections,maxActive);
      evictConnections=new DruidConnectionHolder[maxActive];
      keepAliveConnections=new DruidConnectionHolder[maxActive];
    }
 else {
      this.connections=Arrays.copyOf(this.connections,allCount);
      evictConnections=new DruidConnectionHolder[allCount];
      keepAliveConnections=new DruidConnectionHolder[allCount];
    }
    this.maxActive=maxActive;
  }
  finally {
    lock.unlock();
  }
}

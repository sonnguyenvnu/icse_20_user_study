@Override @SneakyThrows protected DataSourceCache createCache(AtomikosDataSourceConfig config){
  AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
  config.putProperties(atomikosDataSourceBean);
  atomikosDataSourceBean.setBeanName("dynamic_ds_" + config.getId());
  atomikosDataSourceBean.setUniqueResourceName("dynamic_ds_" + config.getId());
  AtomicInteger successCounter=new AtomicInteger();
  CountDownLatch downLatch=new CountDownLatch(1);
  DynamicDataSourceProxy proxy=new DynamicDataSourceProxy(config.getId(),atomikosDataSourceBean);
  if (config.getDatabaseType() != null) {
    proxy.setDatabaseType(config.getDatabaseType());
  }
  DataSourceCache cache=new DataSourceCache(config.hashCode(),proxy,downLatch,config){
    @Override public void closeDataSource(){
      super.closeDataSource();
      atomikosDataSourceBean.close();
      XADataSource dataSource=atomikosDataSourceBean.getXaDataSource();
      if (dataSource instanceof Closeable) {
        try {
          ((Closeable)dataSource).close();
        }
 catch (        IOException e) {
          logger.error("close xa datasource error",e);
        }
      }
 else {
        logger.warn("XADataSource is not instanceof Closeable!",(Object)Thread.currentThread().getStackTrace());
      }
    }
  }
;
  executor.execute(() -> {
    try {
      atomikosDataSourceBean.init();
      successCounter.incrementAndGet();
      downLatch.countDown();
    }
 catch (    Exception e) {
      logger.error("init datasource {} error",config.getId(),e);
    }
  }
);
  executor.execute(() -> {
    try {
      Thread.sleep(config.getInitTimeout() * 1000L);
    }
 catch (    InterruptedException ignored) {
      logger.warn(ignored.getMessage(),ignored);
      Thread.currentThread().interrupt();
    }
 finally {
      if (successCounter.get() == 0) {
        logger.error("init timeout ({}ms)",config.getInitTimeout());
        cache.closeDataSource();
        if (downLatch.getCount() > 0) {
          downLatch.countDown();
        }
      }
    }
  }
);
  return cache;
}

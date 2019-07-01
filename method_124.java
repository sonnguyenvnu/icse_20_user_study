@Override public DataSource _XXXXX_(){
  BasicDataSource datasource=new BasicDataSource();
  datasource.setDriverClassName(config.getDriverClassName());
  datasource.setUsername(config.getUsername());
  datasource.setPassword(config.getPassword());
  datasource.setUrl(config.getConnection());
  datasource.setConnectionProperties(config.getConnectionProperties());
  LOGGER.info("Register JDBCDataSourceShutdownHook");
  Runtime.getRuntime().addShutdownHook(new Thread("JDBCDataSourceShutdownHook"){
    @Override public void run(){
      try {
        LOGGER.info("Shutting down data source");
        datasource.close();
      }
 catch (      SQLException e) {
        LOGGER.error("SQLException: {}",e.getMessage(),e);
        throw new IllegalStateException("Failed to close datasource",e);
      }
    }
  }
);
  return datasource;
}
@SneakyThrows public void putProperties(AtomikosDataSourceBean atomikosDataSourceBean){
  if (null != xaProperties) {
    xaProperties.entrySet().forEach(entry -> entry.setValue(String.valueOf(entry.getValue())));
  }
  XADataSource dataSource=(XADataSource)Class.forName(getXaDataSourceClassName()).newInstance();
  FastBeanCopier.copy(xaProperties,dataSource);
  atomikosDataSourceBean.setXaDataSource(dataSource);
  atomikosDataSourceBean.setXaDataSourceClassName(getXaDataSourceClassName());
  atomikosDataSourceBean.setBorrowConnectionTimeout(getBorrowConnectionTimeout());
  if (loginTimeout != 0) {
    try {
      atomikosDataSourceBean.setLoginTimeout(getLoginTimeout());
    }
 catch (    SQLException e) {
      log.warn(e.getMessage(),e);
    }
  }
  atomikosDataSourceBean.setMaxIdleTime(getMaxIdleTime());
  atomikosDataSourceBean.setMaxPoolSize(getMaxPoolSize());
  atomikosDataSourceBean.setMinPoolSize(getMinPoolSize());
  atomikosDataSourceBean.setDefaultIsolationLevel(getDefaultIsolationLevel());
  atomikosDataSourceBean.setMaintenanceInterval(getMaintenanceInterval());
  atomikosDataSourceBean.setReapTimeout(getReapTimeout());
  atomikosDataSourceBean.setTestQuery(getTestQuery());
  atomikosDataSourceBean.setXaProperties(getXaProperties());
  atomikosDataSourceBean.setMaxLifetime(getMaxLifetime());
}

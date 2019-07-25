@SuppressWarnings("rawtypes") @Override public void configure(Map configurationValues){
  try {
    DruidDataSourceFactory.config(dataSource,configurationValues);
    CSDNStatFilterstat statFilterstat=new CSDNStatFilterstat();
    statFilterstat.configFromProperties(dataSource.getConnectProperties());
    dataSource.getProxyFilters().add(statFilterstat);
  }
 catch (  SQLException e) {
    throw new IllegalArgumentException("config error",e);
  }
}

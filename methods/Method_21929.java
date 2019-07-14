private DataSource setUpEventTraceDataSource(){
  BasicDataSource result=new BasicDataSource();
  result.setDriverClassName(eventTraceDataSourceConfiguration.getDriver());
  result.setUrl(eventTraceDataSourceConfiguration.getUrl());
  result.setUsername(eventTraceDataSourceConfiguration.getUsername());
  result.setPassword(eventTraceDataSourceConfiguration.getPassword());
  return result;
}

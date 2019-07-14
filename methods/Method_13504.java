@Bean(initMethod="init",destroyMethod="close") public DruidDataSource storageDataSource() throws SQLException {
  Environment environment=applicationContext.getEnvironment();
  String ip=environment.getProperty("mysql.server.ip");
  String port=environment.getProperty("mysql.server.port");
  String dbName=environment.getProperty("mysql.db.name");
  String userName=environment.getProperty("mysql.user.name");
  String password=environment.getProperty("mysql.user.password");
  DruidDataSource druidDataSource=new DruidDataSource();
  druidDataSource.setUrl("jdbc:mysql://" + ip + ":" + port + "/" + dbName + "?serverTimezone=UTC");
  druidDataSource.setUsername(userName);
  druidDataSource.setPassword(password);
  druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
  druidDataSource.setInitialSize(0);
  druidDataSource.setMaxActive(180);
  druidDataSource.setMaxWait(60000);
  druidDataSource.setMinIdle(0);
  druidDataSource.setValidationQuery("Select 'x' from DUAL");
  druidDataSource.setTestOnBorrow(false);
  druidDataSource.setTestOnReturn(false);
  druidDataSource.setTestWhileIdle(true);
  druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
  druidDataSource.setMinEvictableIdleTimeMillis(25200000);
  druidDataSource.setRemoveAbandoned(true);
  druidDataSource.setRemoveAbandonedTimeout(1800);
  druidDataSource.setLogAbandoned(true);
  druidDataSource.setFilters("mergeStat");
  return druidDataSource;
}

@Bean(initMethod="init",destroyMethod="close") public DruidDataSource storageDataSource() throws SQLException {
  Environment env=applicationContext.getEnvironment();
  String ip=env.getProperty("mysql.server.ip");
  String port=env.getProperty("mysql.server.port");
  String dbName=env.getProperty("mysql.db.name");
  String userName=env.getProperty("mysql.user.name");
  String password=env.getProperty("mysql.user.password");
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

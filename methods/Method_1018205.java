private DataSource create(boolean proxyUser,String principal){
  String prefix=getPrefixWithTrailingDot();
  String driverClassName=env.getProperty(prefix + "driverClassName");
  String url=env.getProperty(prefix + "url");
  String password=env.getProperty(prefix + "password");
  String userName=env.getProperty(prefix + "username");
  String username=userName;
  if (proxyUser && propertyPrefix.equals(UsernameCaseStrategyUtil.hiveDatasourcePrefix)) {
    UsernameCaseStrategyUtil.UsernameCaseStrategy usernameCaseStrategy=usernameCaseStrategyUtil.getHiveUsernameCaseStrategy();
    String proxyUsername=UsernameCaseStrategyUtil.convertUsernameCase(principal,usernameCaseStrategy);
    url=url + ";hive.server2.proxy.user=" + proxyUsername;
  }
  log.debug("The JDBC URL is " + url + " --- User impersonation enabled: " + proxyUser);
  DataSource ds=DataSourceBuilder.create().driverClassName(driverClassName).url(url).username(username).password(password).build();
  return ds;
}

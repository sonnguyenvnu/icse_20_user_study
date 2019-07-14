/** 
 * ???????????????
 * @param url
 * @param userName
 * @param passwod
 * @return
 */
public HikariDataSource getHikariDataSource(String url,String userName,String passwod){
  com.zaxxer.hikari.HikariConfig config=new com.zaxxer.hikari.HikariConfig();
  config.setMinimumIdle(minIdle);
  config.setMaximumPoolSize(maxActive);
  config.setConnectionTestQuery(validationQuery);
  config.setJdbcUrl(url);
  config.setUsername(userName);
  config.setPassword(passwod);
  config.setConnectionTimeout(connectionTimeout);
  config.setDriverClassName(driverClassName);
  config.setMaxLifetime(maxLeftTime);
  config.setValidationTimeout(validationTimeout);
  config.setConnectionInitSql(connectionInitSql);
  return new HikariDataSource(config);
}

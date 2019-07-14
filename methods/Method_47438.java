/** 
 * ??Hikari?????
 * @param url ???
 * @return ?????
 */
public HikariDataSource getHikariDataSource(String url){
  com.zaxxer.hikari.HikariConfig config=new com.zaxxer.hikari.HikariConfig();
  config.setMinimumIdle(minIdle);
  config.setMaximumPoolSize(maxActive);
  config.setConnectionTestQuery(validationQuery);
  config.setJdbcUrl(url);
  config.setUsername(userName);
  config.setPassword(password);
  config.setDriverClassName(driverClassName);
  config.setConnectionTimeout(connectionTimeout);
  config.setMaxLifetime(maxLeftTime);
  config.setValidationTimeout(validationTimeout);
  config.setConnectionInitSql(connectionInitSql);
  return new HikariDataSource(config);
}

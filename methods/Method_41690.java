/** 
 * Create the underlying C3PO ComboPooledDataSource with the  default supported properties.
 * @throws SchedulerException
 */
private void initialize(String dbDriver,String dbURL,String dbUser,String dbPassword,int maxConnections,int maxStatementsPerConnection,String dbValidationQuery,boolean validateOnCheckout,int idleValidationSeconds,int maxIdleSeconds) throws SQLException, SchedulerException {
  if (dbURL == null) {
    throw new SQLException("DBPool could not be created: DB URL cannot be null");
  }
  if (dbDriver == null) {
    throw new SQLException("DBPool '" + dbURL + "' could not be created: " + "DB driver class name cannot be null!");
  }
  if (maxConnections < 0) {
    throw new SQLException("DBPool '" + dbURL + "' could not be created: " + "Max connections must be greater than zero!");
  }
  datasource=new ComboPooledDataSource();
  try {
    datasource.setDriverClass(dbDriver);
  }
 catch (  PropertyVetoException e) {
    throw new SchedulerException("Problem setting driver class name on datasource: " + e.getMessage(),e);
  }
  datasource.setJdbcUrl(dbURL);
  datasource.setUser(dbUser);
  datasource.setPassword(dbPassword);
  datasource.setMaxPoolSize(maxConnections);
  datasource.setMinPoolSize(1);
  datasource.setMaxIdleTime(maxIdleSeconds);
  datasource.setMaxStatementsPerConnection(maxStatementsPerConnection);
  if (dbValidationQuery != null) {
    datasource.setPreferredTestQuery(dbValidationQuery);
    if (!validateOnCheckout)     datasource.setTestConnectionOnCheckin(true);
 else     datasource.setTestConnectionOnCheckout(true);
    datasource.setIdleConnectionTestPeriod(idleValidationSeconds);
  }
}

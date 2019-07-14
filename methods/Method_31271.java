/** 
 * Initializes the appropriate Database class for the database product used by the data source.
 * @param configuration The Flyway configuration.
 * @param printInfo     Where the DB info should be printed in the logs.
 * @return The appropriate Database class.
 */
public static Database createDatabase(Configuration configuration,boolean printInfo,JdbcConnectionFactory jdbcConnectionFactory){
  OracleDatabase.enableTnsnamesOraSupport();
  String databaseProductName=jdbcConnectionFactory.getProductName();
  if (printInfo) {
    LOG.info("Database: " + jdbcConnectionFactory.getJdbcUrl() + " (" + databaseProductName + ")");
    LOG.debug("Driver  : " + jdbcConnectionFactory.getDriverInfo());
  }
  DatabaseType databaseType=jdbcConnectionFactory.getDatabaseType();
  Database database=createDatabase(databaseType,configuration,jdbcConnectionFactory);
  if (!database.supportsChangingCurrentSchema() && configuration.getSchemas().length > 0) {
    LOG.warn(databaseProductName + " does not support setting the schema for the current session. " + "Default schema will NOT be changed to " + configuration.getSchemas()[0] + " !");
  }
  return database;
}

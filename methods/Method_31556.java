/** 
 * Opens a new connection from this dataSource.
 * @return The new connection.
 * @throws FlywayException when the connection could not be opened.
 */
public Connection openConnection() throws FlywayException {
  Connection connection=firstConnection == null ? JdbcUtils.openConnection(dataSource,connectRetries) : firstConnection;
  firstConnection=null;
  if (connectionInitializer != null) {
    connectionInitializer.initialize(this,connection);
  }
  return connection;
}

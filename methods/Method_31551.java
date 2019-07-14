/** 
 * Build properties for the Driver, including the given user and password (if any), and obtain a corresponding Connection.
 * @param username the name of the user
 * @param password the password to use
 * @return the obtained Connection
 * @throws SQLException in case of failure
 * @see java.sql.Driver#connect(String,java.util.Properties)
 */
protected Connection getConnectionFromDriver(String username,String password) throws SQLException {
  Properties props=new Properties(this.defaultProps);
  if (username != null) {
    props.setProperty("user",username);
  }
  if (password != null) {
    props.setProperty("password",password);
  }
  Connection connection=driver.connect(url,props);
  if (connection == null) {
    throw new FlywayException("Unable to connect to " + url);
  }
  connection.setAutoCommit(autoCommit);
  return connection;
}

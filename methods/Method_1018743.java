/** 
 * Sets up the basic properties for  {@link DataSource}s
 */
private void setup(SQLServerDataSource source,Properties props){
  if (props == null) {
    return;
  }
  if (props.containsKey(JDBC_DATABASE_NAME)) {
    source.setDatabaseName(props.getProperty(JDBC_DATABASE_NAME));
  }
  if (props.containsKey(JDBC_DATASOURCE_NAME)) {
    osgiLogger.log(Level.WARNING,NOT_SUPPORTED_MSG,JDBC_DATASOURCE_NAME);
  }
  if (props.containsKey(JDBC_DESCRIPTION)) {
    source.setDescription(props.getProperty(JDBC_DESCRIPTION));
  }
  if (props.containsKey(JDBC_NETWORK_PROTOCOL)) {
    osgiLogger.log(Level.WARNING,NOT_SUPPORTED_MSG,JDBC_NETWORK_PROTOCOL);
  }
  if (props.containsKey(JDBC_PASSWORD)) {
    source.setPassword(props.getProperty(JDBC_PASSWORD));
  }
  if (props.containsKey(JDBC_PORT_NUMBER)) {
    source.setPortNumber(Integer.parseInt(props.getProperty(JDBC_PORT_NUMBER)));
  }
  if (props.containsKey(JDBC_ROLE_NAME)) {
    osgiLogger.log(Level.WARNING,NOT_SUPPORTED_MSG,JDBC_ROLE_NAME);
  }
  if (props.containsKey(JDBC_SERVER_NAME)) {
    source.setServerName(props.getProperty(JDBC_SERVER_NAME));
  }
  if (props.containsKey(JDBC_URL)) {
    source.setURL(props.getProperty(JDBC_URL));
  }
  if (props.containsKey(JDBC_USER)) {
    source.setUser(props.getProperty(JDBC_USER));
  }
}

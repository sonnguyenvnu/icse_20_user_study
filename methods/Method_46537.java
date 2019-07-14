public Connection proxy(Connection connection){
  return ConnectionWrapper.wrap(connection,compoundJdbcEventListener,ConnectionInformation.fromConnection(connection));
}

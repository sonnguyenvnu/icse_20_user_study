public Long save(Connection connection) throws SQLException {
  if (this.schema == null)   throw new RuntimeException("Uninitialized schema!");
  this.schemaID=findSchemaForPositionSHA(connection,getPositionSHA());
  if (this.schemaID != null)   return schemaID;
  try {
    connection.setAutoCommit(false);
    this.schemaID=saveSchema(connection);
    connection.commit();
  }
 catch (  MySQLIntegrityConstraintViolationException e) {
    connection.rollback();
    connection.setAutoCommit(true);
    this.schemaID=findSchemaForPositionSHA(connection,getPositionSHA());
  }
 finally {
    connection.setAutoCommit(true);
  }
  return schemaID;
}

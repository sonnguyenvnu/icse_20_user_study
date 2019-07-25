public void clone(Long serverID,Position position) throws SchemaStoreException {
  List<ResolvedSchemaChange> empty=Collections.emptyList();
  try (Connection c=maxwellConnectionPool.getConnection()){
    getSchema();
    MysqlSavedSchema cloned=new MysqlSavedSchema(serverID,caseSensitivity,getSchema(),position,savedSchema.getSchemaID(),empty);
    Long schemaId=cloned.save(c);
    LOGGER.info("clone schema @" + position + " based on id " + savedSchema.getSchemaID() + ", new schema id is " + schemaId);
  }
 catch (  SQLException e) {
    throw new SchemaStoreException(e);
  }
}

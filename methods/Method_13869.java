@Override public DatabaseInfo connect(DatabaseConfiguration dbConfig) throws DatabaseServiceException {
  return getMetadata(dbConfig);
}

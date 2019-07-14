public DatabaseConfig getConfiguration() throws BackendException {
  try {
    return db.getConfig();
  }
 catch (  DatabaseException e) {
    throw new PermanentBackendException(e);
  }
}

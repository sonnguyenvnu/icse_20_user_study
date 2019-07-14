boolean initializeCompactStorage() throws PermanentBackendException {
  try {
    final ResultSet versionResultSet=this.session.execute(select().column("release_version").from("system","local"));
    final String version=versionResultSet.one().getString(0);
    final int major=Integer.parseInt(version.substring(0,version.indexOf(".")));
    return (major < 3);
  }
 catch (  NumberFormatException|NoHostAvailableException|QueryExecutionException|QueryValidationException e) {
    throw new PermanentBackendException("Error determining Cassandra version",e);
  }
}

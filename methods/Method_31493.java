@Override protected void doRestoreOriginalState() throws SQLException {
  setCurrentDatabase(originalDatabaseName);
  if (!database.isAzure()) {
    jdbcTemplate.execute("SET ANSI_NULLS " + originalAnsiNulls);
  }
}

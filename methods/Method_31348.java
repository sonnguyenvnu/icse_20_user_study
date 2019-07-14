@Override protected String getCurrentSchemaNameOrSearchPath() throws SQLException {
  return getJdbcConnection().getMetaData().getUserName();
}

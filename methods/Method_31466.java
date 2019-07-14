@Override protected String getCurrentSchemaNameOrSearchPath() throws SQLException {
  return jdbcTemplate.queryForString("SELECT CURRENT_SCHEMA FROM DUMMY");
}

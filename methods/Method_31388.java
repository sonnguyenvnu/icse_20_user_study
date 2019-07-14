@Override protected String getCurrentSchemaNameOrSearchPath() throws SQLException {
  return jdbcTemplate.queryForString("SELECT SYS_CONTEXT('USERENV', 'CURRENT_SCHEMA') FROM DUAL");
}

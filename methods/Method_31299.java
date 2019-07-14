@Override protected String getCurrentSchemaNameOrSearchPath() throws SQLException {
  return jdbcTemplate.queryForString("SELECT CURRENT SCHEMA FROM SYSIBM.SYSDUMMY1");
}

@Override protected String getCurrentSchemaNameOrSearchPath() throws SQLException {
  return jdbcTemplate.queryForString("select current_schema from sysibm.sysdummy1");
}

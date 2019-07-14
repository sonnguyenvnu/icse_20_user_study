@Override protected String getCurrentSchemaNameOrSearchPath() throws SQLException {
  return jdbcTemplate.queryForString("SHOW database");
}

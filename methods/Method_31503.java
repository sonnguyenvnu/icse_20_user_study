@Override protected boolean doExists() throws SQLException {
  return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME=?",name) > 0;
}

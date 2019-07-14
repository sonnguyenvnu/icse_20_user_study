@Override protected boolean doExists() throws SQLException {
  return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM SYS.SCHEMAS WHERE SCHEMA_NAME=?",name) > 0;
}

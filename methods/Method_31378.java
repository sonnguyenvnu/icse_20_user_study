@Override protected boolean doExists() throws SQLException {
  return jdbcTemplate.queryForInt("SELECT (SELECT 1 FROM information_schema.schemata WHERE schema_name=? LIMIT 1)",name) > 0;
}

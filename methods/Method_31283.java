@Override protected boolean doExists() throws SQLException {
  return jdbcTemplate.queryForInt("SELECT count(*) from (" + "SELECT 1 FROM syscat.schemata WHERE schemaname=?" + ")",name) > 0;
}

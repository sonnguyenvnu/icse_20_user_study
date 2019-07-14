@Override protected boolean doExists() throws SQLException {
  return jdbcTemplate.queryForInt("SELECT COUNT (*) FROM sys.sysschemas WHERE schemaname=?",name) > 0;
}

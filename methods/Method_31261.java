@Override protected boolean doExists() throws SQLException {
  return jdbcTemplate.queryForBoolean("SELECT EXISTS ( SELECT 1 FROM pg_database WHERE datname=? )",name);
}

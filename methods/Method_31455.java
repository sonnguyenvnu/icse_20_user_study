@Override protected boolean doExists() throws SQLException {
  return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM pg_namespace WHERE nspname=?",name) > 0;
}

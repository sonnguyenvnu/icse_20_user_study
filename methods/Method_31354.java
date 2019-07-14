@Override protected boolean doExists() throws SQLException {
  return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM systables where owner = ? and tabid > 99",name) > 0;
}

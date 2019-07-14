@Override protected boolean doExists() throws SQLException {
  return jdbcTemplate.queryForString("SELECT object_id('" + name + "')") != null;
}

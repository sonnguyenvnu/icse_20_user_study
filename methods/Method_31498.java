@Override protected String doGetCurrentUser() throws SQLException {
  return getMainConnection().getJdbcTemplate().queryForString("SELECT SUSER_SNAME()");
}

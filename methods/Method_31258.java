@Override protected String doGetCurrentUser() throws SQLException {
  return getMainConnection().getJdbcTemplate().queryForString("(SELECT * FROM [SHOW SESSION_USER])");
}

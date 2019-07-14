@Override protected String doGetCurrentUser() throws SQLException {
  return getMainConnection().getJdbcTemplate().queryForString("select CURRENT_USER from sysibm.sysdummy1");
}

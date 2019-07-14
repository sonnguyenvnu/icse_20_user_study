@Override protected String doGetCurrentUser() throws SQLException {
  return getMainConnection().getJdbcTemplate().queryForString("SELECT CURRENT_USER FROM SYSIBM.SYSDUMMY1");
}

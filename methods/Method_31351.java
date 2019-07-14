@Override protected String doGetCurrentUser() throws SQLException {
  return getJdbcMetaData().getUserName();
}

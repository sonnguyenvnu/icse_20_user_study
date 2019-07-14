protected String doGetCurrentUser() throws SQLException {
  return jdbcMetaData.getUserName();
}

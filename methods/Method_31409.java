@Override protected boolean doExists() throws SQLException {
  return database.queryReturnsRows("SELECT * FROM ALL_USERS WHERE USERNAME = ?",name);
}

@Override protected boolean doExists() throws SQLException {
  try {
    doAllTables();
    return true;
  }
 catch (  SQLException e) {
    return false;
  }
}

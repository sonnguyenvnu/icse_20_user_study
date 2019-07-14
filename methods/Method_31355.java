@Override protected boolean doEmpty() throws SQLException {
  return doAllTables().length == 0;
}

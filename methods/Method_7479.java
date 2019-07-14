public SQLitePreparedStatement stepThis() throws SQLiteException {
  step(sqliteStatementHandle);
  return this;
}

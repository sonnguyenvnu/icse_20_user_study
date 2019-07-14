public SQLitePreparedStatement executeFast(String sql) throws SQLiteException {
  return new SQLitePreparedStatement(this,sql,true);
}

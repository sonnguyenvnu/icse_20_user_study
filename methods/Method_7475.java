public boolean tableExists(String tableName) throws SQLiteException {
  checkOpened();
  String s="SELECT rowid FROM sqlite_master WHERE type='table' AND name=?;";
  return executeInt(s,tableName) != null;
}

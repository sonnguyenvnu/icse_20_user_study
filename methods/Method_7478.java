public SQLiteCursor query(Object[] args) throws SQLiteException {
  if (args == null) {
    throw new IllegalArgumentException();
  }
  checkFinalized();
  reset(sqliteStatementHandle);
  int i=1;
  for (int a=0; a < args.length; a++) {
    Object obj=args[a];
    if (obj == null) {
      bindNull(sqliteStatementHandle,i);
    }
 else     if (obj instanceof Integer) {
      bindInt(sqliteStatementHandle,i,(Integer)obj);
    }
 else     if (obj instanceof Double) {
      bindDouble(sqliteStatementHandle,i,(Double)obj);
    }
 else     if (obj instanceof String) {
      bindString(sqliteStatementHandle,i,(String)obj);
    }
 else     if (obj instanceof Long) {
      bindLong(sqliteStatementHandle,i,(Long)obj);
    }
 else {
      throw new IllegalArgumentException();
    }
    i++;
  }
  return new SQLiteCursor(this);
}

@Override public boolean canHandle(@NonNull File file) throws IOException {
  if (!isSQLite3File(file))   return false;
  Database db=opener.open(file);
  Cursor c=db.query("select count(*) from SQLITE_MASTER " + "where name='tracks' or name='track2groups'");
  boolean result=(c.moveToNext() && c.getInt(0) == 2);
  c.close();
  db.close();
  return result;
}

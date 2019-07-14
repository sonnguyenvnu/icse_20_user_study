@Override public boolean canHandle(@NonNull File file) throws IOException {
  if (!isSQLite3File(file))   return false;
  Database db=opener.open(file);
  boolean canHandle=true;
  Cursor c=db.query("select count(*) from SQLITE_MASTER " + "where name='Habits' or name='Repetitions'");
  if (!c.moveToNext() || c.getInt(0) != 2) {
    canHandle=false;
  }
  if (db.getVersion() > DATABASE_VERSION) {
    canHandle=false;
  }
  c.close();
  db.close();
  return canHandle;
}

@Override public int getVersion(){
  try (Cursor c=query("PRAGMA user_version")){
    c.moveToNext();
    return c.getInt(0);
  }
 }

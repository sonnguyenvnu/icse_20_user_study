public int getFoldersCount(int status){
  int c=0;
  SQLiteDatabase db=getReadableDatabase();
  Cursor cur=db.query(TABLE_ALBUMS,new String[]{"count(*)"},ALBUM_STATUS + "=?",new String[]{String.valueOf(status)},null,null,null);
  if (cur.moveToFirst())   c=cur.getInt(0);
  cur.close();
  db.close();
  return c;
}

private static boolean exist(SQLiteDatabase db,String path){
  Cursor cur=db.rawQuery(String.format("SELECT EXISTS(SELECT 1 FROM %s WHERE %s=? LIMIT 1)",TABLE_ALBUMS,ALBUM_PATH),new String[]{path});
  boolean tracked=cur.moveToFirst() && cur.getInt(0) == 1;
  cur.close();
  return tracked;
}

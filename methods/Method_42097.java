@NonNull public static AlbumSettings getSettings(SQLiteDatabase db,String path){
  Cursor cursor=null;
  try {
    if (exist(db,path)) {
      cursor=db.query(TABLE_ALBUMS,StringUtils.asArray(ALBUM_COVER_PATH,ALBUM_SORTING_MODE,ALBUM_SORTING_ORDER,ALBUM_PINNED),ALBUM_PATH + "=?",new String[]{path},null,null,null);
      if (cursor.moveToFirst())       return new AlbumSettings(cursor.getString(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3));
    }
 else     db.insert(TABLE_ALBUMS,null,getDefaults(path));
    return AlbumSettings.getDefaults();
  }
  finally {
    if (cursor != null)     cursor.close();
  }
}

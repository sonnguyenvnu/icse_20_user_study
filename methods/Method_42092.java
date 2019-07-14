public void clearStatusFolder(String path){
  SQLiteDatabase db=getWritableDatabase();
  ContentValues values=new ContentValues();
  values.put(ALBUM_STATUS,"");
  if (exist(db,path))   db.update(TABLE_ALBUMS,values,ALBUM_PATH + "=?",new String[]{path});
  db.close();
}

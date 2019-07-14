private void changeSatusAlbum(SQLiteDatabase db,Album album,int status){
  ContentValues values=new ContentValues();
  values.put(ALBUM_STATUS,status);
  if (exist(db,album.getPath())) {
    db.update(TABLE_ALBUMS,values,ALBUM_PATH + "=?",new String[]{album.getPath()});
  }
 else {
    values.put(ALBUM_PATH,album.getPath());
    values.put(ALBUM_PINNED,0);
    values.put(ALBUM_SORTING_MODE,SortingMode.DATE.getValue());
    values.put(ALBUM_SORTING_ORDER,SortingOrder.DESCENDING.getValue());
    values.put(ALBUM_ID,album.getId());
    db.insert(TABLE_ALBUMS,null,values);
  }
}

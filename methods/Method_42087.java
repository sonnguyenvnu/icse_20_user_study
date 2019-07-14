public void excludeAlbum(String path){
  SQLiteDatabase db=getWritableDatabase();
  excludeAlbum(db,Album.withPath(path));
  db.close();
}

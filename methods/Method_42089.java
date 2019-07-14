public void addFolderToWhiteList(String path){
  SQLiteDatabase db=getWritableDatabase();
  changeSatusAlbum(db,Album.withPath(path),INCLUDED);
  db.close();
}

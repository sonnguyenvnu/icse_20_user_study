public void setPined(String path,boolean pinned){
  ContentValues values=new ContentValues();
  values.put(ALBUM_PINNED,pinned ? 1 : 0);
  setValue(path,values);
}

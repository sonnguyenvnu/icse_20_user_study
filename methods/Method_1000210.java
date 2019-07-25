public void clear(){
  final SQLiteDatabase database=getWritableDatabase();
  database.delete(SongPlayCountColumns.NAME,null,null);
}

public void clear(){
  final SQLiteDatabase database=getWritableDatabase();
  database.delete(RecentStoreColumns.NAME,null,null);
}

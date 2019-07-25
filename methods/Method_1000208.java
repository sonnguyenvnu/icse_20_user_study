public boolean contains(File file){
  if (file == null) {
    return false;
  }
  String path=FileUtil.safeGetCanonicalPath(file);
  final SQLiteDatabase database=getReadableDatabase();
  Cursor cursor=database.query(BlacklistStoreColumns.NAME,new String[]{BlacklistStoreColumns.PATH},BlacklistStoreColumns.PATH + "=?",new String[]{path},null,null,null,null);
  boolean containsPath=cursor != null && cursor.moveToFirst();
  if (cursor != null) {
    cursor.close();
  }
  return containsPath;
}

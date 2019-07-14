@Nullable public Sort findEntry(String path){
  if (TextUtils.isEmpty(path)) {
    return null;
  }
  String query="Select * FROM " + TABLE_SORT + " WHERE " + COLUMN_SORT_PATH + "= \"" + path + "\"";
  SQLiteDatabase sqLiteDatabase=getReadableDatabase();
  Cursor cursor=sqLiteDatabase.rawQuery(query,null);
  Sort sort;
  if (cursor.moveToFirst()) {
    sort=new Sort(cursor.getString(0),cursor.getInt(1));
    cursor.close();
  }
 else {
    sort=null;
  }
  return sort;
}

public void updateEntry(Sort oldSort,Sort newSort){
  if (TextUtils.isEmpty(oldSort.path) || TextUtils.isEmpty(newSort.path)) {
    return;
  }
  SQLiteDatabase sqLiteDatabase=getWritableDatabase();
  ContentValues contentValues=new ContentValues();
  contentValues.put(COLUMN_SORT_PATH,newSort.path);
  contentValues.put(COLUMN_SORT_TYPE,newSort.type);
  sqLiteDatabase.update(TABLE_SORT,contentValues,COLUMN_SORT_PATH + " = ?",new String[]{oldSort.path});
}

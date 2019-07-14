public ArrayList<String[]> getBookmarksList(){
  SQLiteDatabase sqLiteDatabase=getReadableDatabase();
  Cursor cursor=sqLiteDatabase.query(getTableForOperation(Operation.BOOKMARKS),null,null,null,null,null,null);
  boolean hasNext=cursor.moveToFirst();
  ArrayList<String[]> row=new ArrayList<>();
  while (hasNext) {
    row.add(new String[]{cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),cursor.getString(cursor.getColumnIndex(COLUMN_PATH))});
    hasNext=cursor.moveToNext();
  }
  cursor.close();
  return row;
}

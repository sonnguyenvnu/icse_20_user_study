public LinkedList<String> getHistoryLinkedList(){
  SQLiteDatabase sqLiteDatabase=getReadableDatabase();
  Cursor cursor=sqLiteDatabase.query(getTableForOperation(Operation.HISTORY),null,null,null,null,null,null);
  LinkedList<String> paths=new LinkedList<>();
  boolean hasNext=cursor.moveToFirst();
  while (hasNext) {
    paths.push(cursor.getString(cursor.getColumnIndex(COLUMN_PATH)));
    hasNext=cursor.moveToNext();
  }
  cursor.close();
  return paths;
}

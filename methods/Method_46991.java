private ArrayList<String> getPath(Operation operation){
  SQLiteDatabase sqLiteDatabase=getReadableDatabase();
  Cursor cursor=sqLiteDatabase.query(getTableForOperation(operation),null,null,null,null,null,null);
  ArrayList<String> paths=new ArrayList<>();
switch (operation) {
case LIST:
case GRID:
    boolean hasNext=cursor.moveToFirst();
  while (hasNext) {
    paths.add(cursor.getString(cursor.getColumnIndex(COLUMN_PATH)));
    hasNext=cursor.moveToNext();
  }
cursor.close();
return paths;
default :
return null;
}
}

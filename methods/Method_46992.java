private void renamePath(Operation operation,String name,String path){
  SQLiteDatabase sqLiteDatabase=getWritableDatabase();
  ContentValues contentValues=new ContentValues();
  contentValues.put(COLUMN_NAME,name);
  contentValues.put(COLUMN_PATH,path);
  sqLiteDatabase.update(getTableForOperation(operation),contentValues,COLUMN_PATH + "=?",new String[]{name});
}

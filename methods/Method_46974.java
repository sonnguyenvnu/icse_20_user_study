public void clear(){
  try {
    SQLiteDatabase sqLiteDatabase=getWritableDatabase();
    sqLiteDatabase.delete(TABLE_TAB,COLUMN_TAB_NO + " = ?",new String[]{"" + 1});
    sqLiteDatabase.delete(TABLE_TAB,COLUMN_TAB_NO + " = ?",new String[]{"" + 2});
  }
 catch (  NumberFormatException e) {
    e.printStackTrace();
  }
}

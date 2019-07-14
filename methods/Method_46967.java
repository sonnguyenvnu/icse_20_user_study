public void clear(String path){
  try {
    SQLiteDatabase sqLiteDatabase=getWritableDatabase();
    sqLiteDatabase.delete(TABLE_ENCRYPTED,COLUMN_ENCRYPTED_PATH + " = ?",new String[]{path});
  }
 catch (  NumberFormatException e) {
    e.printStackTrace();
  }
}

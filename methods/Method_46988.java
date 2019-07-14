private String getSshAuthPrivateKeyColumn(String uri,String columnName){
  SQLiteDatabase sqLiteDatabase=getReadableDatabase();
  Cursor result=sqLiteDatabase.query(TABLE_SFTP,new String[]{columnName},COLUMN_PATH + " = ?",new String[]{uri},null,null,null);
  if (result.moveToFirst()) {
    try {
      return result.getString(0);
    }
  finally {
      result.close();
    }
  }
 else {
    result.close();
    return null;
  }
}

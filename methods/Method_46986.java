public ArrayList<String[]> getSmbList(){
  SQLiteDatabase sqLiteDatabase=getReadableDatabase();
  Cursor cursor=sqLiteDatabase.query(getTableForOperation(Operation.SMB),null,null,null,null,null,null);
  boolean hasNext=cursor.moveToFirst();
  ArrayList<String[]> row=new ArrayList<>();
  while (hasNext) {
    try {
      row.add(new String[]{cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),SmbUtil.getSmbDecryptedPath(context,cursor.getString(cursor.getColumnIndex(COLUMN_PATH)))});
    }
 catch (    GeneralSecurityException|IOException e) {
      e.printStackTrace();
      Toast.makeText(context,context.getString(R.string.failed_smb_decrypt_path),Toast.LENGTH_LONG).show();
      removeSmbPath(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),"");
      continue;
    }
    hasNext=cursor.moveToNext();
  }
  cursor.close();
  return row;
}

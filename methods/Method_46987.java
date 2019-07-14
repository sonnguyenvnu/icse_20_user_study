public List<String[]> getSftpList(){
  SQLiteDatabase sqLiteDatabase=getReadableDatabase();
  Cursor cursor=sqLiteDatabase.query(getTableForOperation(Operation.SFTP),new String[]{COLUMN_NAME,COLUMN_PATH},null,null,null,null,COLUMN_ID);
  boolean hasNext=cursor.moveToFirst();
  ArrayList<String[]> retval=new ArrayList<String[]>();
  while (hasNext) {
    String path=SshClientUtils.decryptSshPathAsNecessary(cursor.getString(cursor.getColumnIndex(COLUMN_PATH)));
    if (path == null) {
      Log.e("ERROR","Error decrypting path: " + cursor.getString(cursor.getColumnIndex(COLUMN_PATH)));
      Toast.makeText(context,context.getString(R.string.failed_smb_decrypt_path),Toast.LENGTH_LONG).show();
      continue;
    }
 else {
      retval.add(new String[]{cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),path});
    }
    hasNext=cursor.moveToNext();
  }
  cursor.close();
  return retval;
}

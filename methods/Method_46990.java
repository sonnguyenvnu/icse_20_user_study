private void removeSftpPath(String name,String path){
  SQLiteDatabase sqLiteDatabase=getWritableDatabase();
  try {
    if (path.equals("")) {
      throw new IOException();
    }
    sqLiteDatabase.delete(TABLE_SFTP,COLUMN_NAME + " = ? AND " + COLUMN_PATH + " = ?",new String[]{name,SshClientUtils.encryptSshPathAsNecessary(path)});
  }
 catch (  IOException e) {
    e.printStackTrace();
    sqLiteDatabase.delete(TABLE_SFTP,COLUMN_NAME + " = ?",new String[]{name});
  }
}

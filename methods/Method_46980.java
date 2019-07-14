public void updateSsh(String connectionName,String oldConnectionName,String path,String sshKeyName,String sshKey){
  SQLiteDatabase database=getWritableDatabase();
  ContentValues values=new ContentValues();
  values.put(COLUMN_NAME,connectionName);
  values.put(COLUMN_PATH,path);
  if (sshKeyName != null && sshKey != null) {
    values.put(COLUMN_PRIVATE_KEY_NAME,sshKeyName);
    values.put(COLUMN_PRIVATE_KEY,sshKey);
  }
  database.update(getTableForOperation(Operation.SFTP),values,String.format("%s=?",COLUMN_NAME),new String[]{oldConnectionName});
}

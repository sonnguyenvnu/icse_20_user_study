public void addSsh(String name,String path,String hostKey,String sshKeyName,String sshKey){
  SQLiteDatabase database=getWritableDatabase();
  ContentValues values=new ContentValues();
  values.put(COLUMN_NAME,name);
  values.put(COLUMN_PATH,path);
  values.put(COLUMN_HOST_PUBKEY,hostKey);
  if (sshKey != null && !"".equals(sshKey)) {
    values.put(COLUMN_PRIVATE_KEY_NAME,sshKeyName);
    values.put(COLUMN_PRIVATE_KEY,sshKey);
  }
  database.insert(getTableForOperation(Operation.SFTP),null,values);
}

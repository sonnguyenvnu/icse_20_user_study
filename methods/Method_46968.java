public EncryptedEntry findEntry(String path) throws GeneralSecurityException, IOException {
  String query="Select * FROM " + TABLE_ENCRYPTED + " WHERE " + COLUMN_ENCRYPTED_PATH + "= \"" + path + "\"";
  SQLiteDatabase sqLiteDatabase=getReadableDatabase();
  Cursor cursor=sqLiteDatabase.rawQuery(query,null);
  EncryptedEntry encryptedEntry=new EncryptedEntry();
  if (cursor.moveToFirst()) {
    encryptedEntry.setId((cursor.getInt(0)));
    encryptedEntry.setPath(cursor.getString(1));
    encryptedEntry.setPassword(CryptUtil.decryptPassword(context,cursor.getString(2)));
    cursor.close();
  }
 else {
    encryptedEntry=null;
  }
  return encryptedEntry;
}

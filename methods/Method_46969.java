public List<EncryptedEntry> getAllEntries() throws GeneralSecurityException, IOException {
  List<EncryptedEntry> entryList=new ArrayList<EncryptedEntry>();
  String query="Select * FROM " + TABLE_ENCRYPTED;
  SQLiteDatabase sqLiteDatabase=getReadableDatabase();
  Cursor cursor=null;
  try {
    cursor=sqLiteDatabase.rawQuery(query,null);
    boolean hasNext=cursor.moveToFirst();
    while (hasNext) {
      EncryptedEntry encryptedEntry=new EncryptedEntry();
      encryptedEntry.setId((cursor.getInt(0)));
      encryptedEntry.setPath(cursor.getString(1));
      encryptedEntry.setPassword(CryptUtil.decryptPassword(context,cursor.getString(2)));
      entryList.add(encryptedEntry);
      hasNext=cursor.moveToNext();
    }
  }
  finally {
    if (cursor != null) {
      cursor.close();
    }
  }
  return entryList;
}

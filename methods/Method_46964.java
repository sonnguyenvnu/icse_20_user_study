public void addEntry(CloudEntry cloudEntry) throws CloudPluginException {
  if (!CloudSheetFragment.isCloudProviderAvailable(context))   throw new CloudPluginException();
  ContentValues contentValues=new ContentValues();
  contentValues.put(COLUMN_CLOUD_SERVICE,cloudEntry.getServiceType().ordinal());
  try {
    contentValues.put(COLUMN_CLOUD_PERSIST,CryptUtil.encryptPassword(context,cloudEntry.getPersistData()));
  }
 catch (  Exception e) {
    e.printStackTrace();
    contentValues.put(COLUMN_CLOUD_PERSIST,cloudEntry.getPersistData());
  }
  SQLiteDatabase sqLiteDatabase=getWritableDatabase();
  sqLiteDatabase.insert(TABLE_CLOUD_PERSIST,null,contentValues);
}

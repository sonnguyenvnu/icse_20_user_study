public void updateEntry(OpenMode serviceType,CloudEntry newCloudEntry) throws CloudPluginException {
  if (!CloudSheetFragment.isCloudProviderAvailable(context))   throw new CloudPluginException();
  SQLiteDatabase sqLiteDatabase=getWritableDatabase();
  ContentValues contentValues=new ContentValues();
  contentValues.put(COLUMN_CLOUD_ID,newCloudEntry.getId());
  contentValues.put(COLUMN_CLOUD_SERVICE,newCloudEntry.getServiceType().ordinal());
  try {
    contentValues.put(COLUMN_CLOUD_PERSIST,CryptUtil.encryptPassword(context,newCloudEntry.getPersistData()));
  }
 catch (  Exception e) {
    e.printStackTrace();
    contentValues.put(COLUMN_CLOUD_PERSIST,newCloudEntry.getPersistData());
  }
  sqLiteDatabase.update(TABLE_CLOUD_PERSIST,contentValues,COLUMN_CLOUD_SERVICE + " = ?",new String[]{serviceType.ordinal() + ""});
}

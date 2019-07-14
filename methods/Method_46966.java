public CloudEntry findEntry(OpenMode serviceType) throws CloudPluginException {
  if (!CloudSheetFragment.isCloudProviderAvailable(context))   throw new CloudPluginException();
  String query="Select * FROM " + TABLE_CLOUD_PERSIST + " WHERE " + COLUMN_CLOUD_SERVICE + "= \"" + serviceType.ordinal() + "\"";
  SQLiteDatabase sqLiteDatabase=getReadableDatabase();
  Cursor cursor=sqLiteDatabase.rawQuery(query,null);
  CloudEntry cloudEntry=new CloudEntry();
  if (cursor.moveToFirst()) {
    cloudEntry.setId((cursor.getInt(0)));
    cloudEntry.setServiceType(serviceType);
    try {
      cloudEntry.setPersistData(CryptUtil.decryptPassword(context,cursor.getString(2)));
    }
 catch (    Exception e) {
      e.printStackTrace();
      cloudEntry.setPersistData("");
      return cloudEntry;
    }
    cursor.close();
  }
 else {
    cloudEntry=null;
  }
  return cloudEntry;
}

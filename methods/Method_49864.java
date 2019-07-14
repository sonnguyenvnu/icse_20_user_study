private static void setErrorType(Context context,String locationUrl,int errorType){
  Long msgId=getId(context,locationUrl);
  if (msgId == null) {
    return;
  }
  Uri.Builder uriBuilder=Telephony.MmsSms.PendingMessages.CONTENT_URI.buildUpon();
  uriBuilder.appendQueryParameter("protocol","mms");
  uriBuilder.appendQueryParameter("message",String.valueOf(msgId));
  Cursor cursor=android.database.sqlite.SqliteWrapper.query(context,context.getContentResolver(),uriBuilder.build(),null,null,null,null);
  if (cursor == null) {
    return;
  }
  try {
    if ((cursor.getCount() == 1) && cursor.moveToFirst()) {
      ContentValues values=new ContentValues();
      values.put(Telephony.MmsSms.PendingMessages.ERROR_TYPE,errorType);
      int columnIndex=cursor.getColumnIndexOrThrow(Telephony.MmsSms.PendingMessages._ID);
      long id=cursor.getLong(columnIndex);
      android.database.sqlite.SqliteWrapper.update(context,context.getContentResolver(),Telephony.MmsSms.PendingMessages.CONTENT_URI,values,Telephony.MmsSms.PendingMessages._ID + "=" + id,null);
    }
  }
  finally {
    cursor.close();
  }
}

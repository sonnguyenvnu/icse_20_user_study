private static boolean isDuplicateNotification(Context context,NotificationInd nInd){
  byte[] rawLocation=nInd.getContentLocation();
  if (rawLocation != null) {
    String location=new String(rawLocation);
    String selection=Mms.CONTENT_LOCATION + " = ?";
    String[] selectionArgs=new String[]{location};
    Cursor cursor=SqliteWrapper.query(context,context.getContentResolver(),Mms.CONTENT_URI,new String[]{Mms._ID},selection,selectionArgs,null);
    if (cursor != null) {
      try {
        if (cursor.getCount() > 0) {
          cursor.close();
        }
      }
  finally {
        cursor.close();
      }
    }
  }
  return false;
}

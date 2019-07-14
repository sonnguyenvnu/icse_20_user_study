private static boolean isNotificationExist(Context context,String location){
  String selection=Telephony.Mms.CONTENT_LOCATION + " = ?";
  String[] selectionArgs=new String[]{location};
  Cursor c=SqliteWrapper.query(context,context.getContentResolver(),Telephony.Mms.CONTENT_URI,new String[]{Telephony.Mms._ID},selection,selectionArgs,null);
  if (c != null) {
    try {
      if (c.getCount() > 0) {
        return true;
      }
    }
  finally {
      c.close();
    }
  }
  return false;
}

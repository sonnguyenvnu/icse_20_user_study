private static Long getId(Context context,String location){
  String selection=Telephony.Mms.CONTENT_LOCATION + " = ?";
  String[] selectionArgs=new String[]{location};
  Cursor c=android.database.sqlite.SqliteWrapper.query(context,context.getContentResolver(),Telephony.Mms.CONTENT_URI,new String[]{Telephony.Mms._ID},selection,selectionArgs,null);
  if (c != null) {
    try {
      if (c.moveToFirst()) {
        return c.getLong(c.getColumnIndex(Telephony.Mms._ID));
      }
    }
  finally {
      c.close();
    }
  }
  return null;
}

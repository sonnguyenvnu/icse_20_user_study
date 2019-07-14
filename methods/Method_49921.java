private static void updateContentLocation(Context context,Uri uri,String contentLocation,boolean locked){
  ContentValues values=new ContentValues(2);
  values.put(Mms.CONTENT_LOCATION,contentLocation);
  values.put(Mms.LOCKED,locked);
  SqliteWrapper.update(context,context.getContentResolver(),uri,values,null,null);
}

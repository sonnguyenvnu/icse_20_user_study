public static boolean doesThreadIdExist(Context context,long threadId){
  Uri uri=Uri.parse("content://mms-sms/conversations/" + threadId + "/");
  Cursor cursor=context.getContentResolver().query(uri,new String[]{"_id"},null,null,null);
  if (cursor != null && cursor.moveToFirst()) {
    cursor.close();
    return true;
  }
 else {
    return false;
  }
}

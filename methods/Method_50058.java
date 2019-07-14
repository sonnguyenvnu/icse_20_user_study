/** 
 * Gets the current thread_id or creates a new one for the given recipient
 * @param context is the context of the activity or service
 * @param recipients is the set of people message is being sent to
 * @return the thread_id to use in the database
 */
public static long getOrCreateThreadId(Context context,Set<String> recipients){
  Uri.Builder uriBuilder=Uri.parse("content://mms-sms/threadID").buildUpon();
  for (  String recipient : recipients) {
    if (isEmailAddress(recipient)) {
      recipient=extractAddrSpec(recipient);
    }
    uriBuilder.appendQueryParameter("recipient",recipient);
  }
  Uri uri=uriBuilder.build();
  Cursor cursor=SqliteWrapper.query(context,context.getContentResolver(),uri,new String[]{"_id"},null,null,null);
  if (cursor != null) {
    try {
      if (cursor.moveToFirst()) {
        long id=cursor.getLong(0);
        cursor.close();
        return id;
      }
    }
  finally {
      cursor.close();
    }
  }
  Random random=new Random();
  return random.nextLong();
}

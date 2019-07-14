private int getRetrieveStatus(long msgID){
  int retrieveStatus=0;
  Cursor cursor=SqliteWrapper.query(mContext,mContentResolver,Mms.Inbox.CONTENT_URI,null,Mms._ID + "=" + msgID,null,null);
  try {
    if (cursor.moveToFirst()) {
      retrieveStatus=cursor.getInt(cursor.getColumnIndexOrThrow(Mms.RESPONSE_STATUS));
    }
  }
  finally {
    cursor.close();
  }
  if (retrieveStatus != 0) {
    Timber.v("Retrieve status is: " + retrieveStatus);
  }
  return retrieveStatus;
}

private int getResponseStatus(long msgID){
  int respStatus=0;
  Cursor cursor=SqliteWrapper.query(mContext,mContentResolver,Mms.Outbox.CONTENT_URI,null,Mms._ID + "=" + msgID,null,null);
  try {
    if (cursor.moveToFirst()) {
      respStatus=cursor.getInt(cursor.getColumnIndexOrThrow(Mms.RESPONSE_STATUS));
    }
  }
  finally {
    cursor.close();
  }
  if (respStatus != 0) {
    Timber.e("Response status is: " + respStatus);
  }
  return respStatus;
}

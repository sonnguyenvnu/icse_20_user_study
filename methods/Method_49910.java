private SmsMessage updateMessageStatus(Context context,Uri messageUri,byte[] pdu,String format){
  SmsMessage message=SmsMessage.createFromPdu(pdu);
  if (message == null) {
    return null;
  }
  Cursor cursor=SqliteWrapper.query(context,context.getContentResolver(),messageUri,ID_PROJECTION,null,null,null);
  if (cursor == null) {
    return null;
  }
  try {
    if (cursor.moveToFirst()) {
      int messageId=cursor.getInt(0);
      Uri updateUri=ContentUris.withAppendedId(STATUS_URI,messageId);
      int status=message.getStatus();
      boolean isStatusReport=message.isStatusReportMessage();
      ContentValues contentValues=new ContentValues(2);
      log("updateMessageStatus: msgUrl=" + messageUri + ", status=" + status + ", isStatusReport=" + isStatusReport);
      contentValues.put(Sms.STATUS,status);
      contentValues.put(Inbox.DATE_SENT,System.currentTimeMillis());
      SqliteWrapper.update(context,context.getContentResolver(),updateUri,contentValues,null,null);
    }
 else {
      error("Can't find message for status update: " + messageUri);
    }
  }
  finally {
    cursor.close();
  }
  return message;
}

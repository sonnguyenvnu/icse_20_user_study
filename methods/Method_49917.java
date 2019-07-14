private static long findThreadId(Context context,GenericPdu pdu,int type){
  String messageId;
  if (type == MESSAGE_TYPE_DELIVERY_IND) {
    messageId=new String(((DeliveryInd)pdu).getMessageId());
  }
 else {
    messageId=new String(((ReadOrigInd)pdu).getMessageId());
  }
  StringBuilder sb=new StringBuilder('(');
  sb.append(Mms.MESSAGE_ID);
  sb.append('=');
  sb.append(DatabaseUtils.sqlEscapeString(messageId));
  sb.append(" AND ");
  sb.append(Mms.MESSAGE_TYPE);
  sb.append('=');
  sb.append(PduHeaders.MESSAGE_TYPE_SEND_REQ);
  Cursor cursor=SqliteWrapper.query(context,context.getContentResolver(),Mms.CONTENT_URI,new String[]{Mms.THREAD_ID},sb.toString(),null,null);
  if (cursor != null) {
    try {
      if ((cursor.getCount() == 1) && cursor.moveToFirst()) {
        long id=cursor.getLong(0);
        cursor.close();
        return id;
      }
    }
  finally {
      cursor.close();
    }
  }
  return -1;
}

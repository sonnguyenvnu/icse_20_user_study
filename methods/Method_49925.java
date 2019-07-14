private void markMmsFailed(final Context context){
  Cursor query=context.getContentResolver().query(Mms.CONTENT_URI,new String[]{Mms._ID},null,null,"date desc");
  try {
    query.moveToFirst();
    String id=query.getString(query.getColumnIndex(Mms._ID));
    query.close();
    ContentValues values=new ContentValues();
    values.put(Mms.MESSAGE_BOX,Mms.MESSAGE_BOX_FAILED);
    String where=Mms._ID + " = '" + id + "'";
    context.getContentResolver().update(Mms.CONTENT_URI,values,where,null);
    BroadcastUtils.sendExplicitBroadcast(mContext,new Intent(),com.klinker.android.send_message.Transaction.REFRESH);
    BroadcastUtils.sendExplicitBroadcast(mContext,new Intent(),com.klinker.android.send_message.Transaction.NOTIFY_SMS_FAILURE);
    BroadcastUtils.sendExplicitBroadcast(mContext,new Intent(),com.klinker.android.send_message.Transaction.MMS_ERROR);
  }
 catch (  Exception e) {
  }
}

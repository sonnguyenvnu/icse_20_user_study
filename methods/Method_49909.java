@Override protected void onHandleIntent(Intent intent){
  String messageUri=intent.getDataString();
  if (messageUri == null) {
    messageUri=intent.getStringExtra("message_uri");
    if (messageUri == null) {
      return;
    }
  }
  byte[] pdu=intent.getByteArrayExtra("pdu");
  String format=intent.getStringExtra("format");
  SmsMessage message=updateMessageStatus(this,Uri.parse(messageUri),pdu,format);
}

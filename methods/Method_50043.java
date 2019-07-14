private void handleHttpError(Context context,Intent intent){
  final int httpError=intent.getIntExtra(SmsManager.EXTRA_MMS_HTTP_STATUS,0);
  if (httpError == 404 || httpError == 400) {
    SqliteWrapper.delete(context,context.getContentResolver(),Telephony.Mms.CONTENT_URI,LOCATION_SELECTION,new String[]{Integer.toString(PduHeaders.MESSAGE_TYPE_NOTIFICATION_IND),intent.getStringExtra(EXTRA_LOCATION_URL)});
  }
}

/** 
 * Process the result of the completed request, including updating the message status in database and sending back the result via pending intents.
 * @param context The context
 * @param result The result code of execution
 * @param response The response body
 * @param httpStatusCode The optional http status code in case of http failure
 */
public void processResult(Context context,int result,byte[] response,int httpStatusCode){
  final Uri messageUri=persistIfRequired(context,result,response);
  final PendingIntent pendingIntent=getPendingIntent();
  if (pendingIntent != null) {
    boolean succeeded=true;
    Intent fillIn=new Intent();
    if (response != null) {
      succeeded=transferResponse(fillIn,response);
    }
    if (messageUri != null) {
      fillIn.putExtra("uri",messageUri.toString());
    }
    if (result == SmsManager.MMS_ERROR_HTTP_FAILURE && httpStatusCode != 0) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
        fillIn.putExtra(SmsManager.EXTRA_MMS_HTTP_STATUS,httpStatusCode);
      }
 else {
        fillIn.putExtra("android.telephony.extra.MMS_HTTP_STATUS",httpStatusCode);
      }
    }
    try {
      if (!succeeded) {
        result=SmsManager.MMS_ERROR_IO_ERROR;
      }
      pendingIntent.send(context,result,fillIn);
    }
 catch (    PendingIntent.CanceledException e) {
      Timber.e(e,"MmsRequest: sending pending intent canceled");
    }
  }
  revokeUriPermission(context);
}

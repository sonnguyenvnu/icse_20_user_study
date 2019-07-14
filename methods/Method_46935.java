/** 
 * Initializes notification views to initial (processing..) state
 */
public void initNotificationViews(){
  getNotificationCustomViewBig().setTextViewText(R.id.notification_service_textView_filename_big,getString(R.string.processing));
  getNotificationCustomViewSmall().setTextViewText(R.id.notification_service_textView_filename_small,getString(R.string.processing));
  String zeroBytesFormat=Formatter.formatFileSize(this,0l);
  getNotificationCustomViewBig().setTextViewText(R.id.notification_service_textView_written_big,zeroBytesFormat);
  getNotificationCustomViewSmall().setTextViewText(R.id.notification_service_textView_written_small,zeroBytesFormat);
  getNotificationCustomViewBig().setTextViewText(R.id.notification_service_textView_transferRate_big,zeroBytesFormat + "/s");
  getNotificationCustomViewBig().setTextViewText(R.id.notification_service_textView_timeRemaining_big,getString(R.string.unknown));
  getNotificationCustomViewSmall().setProgressBar(R.id.notification_service_progressBar_small,0,0,true);
  getNotificationCustomViewBig().setProgressBar(R.id.notification_service_progressBar_big,0,0,true);
  getNotificationManager().notify(getNotificationId(),getNotificationBuilder().build());
}

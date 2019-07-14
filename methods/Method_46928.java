@Override public void progressHalted(){
  getNotificationCustomViewSmall().setProgressBar(R.id.notification_service_progressBar_small,0,0,true);
  getNotificationCustomViewBig().setProgressBar(R.id.notification_service_progressBar_big,0,0,true);
  getNotificationCustomViewBig().setTextViewText(R.id.notification_service_textView_timeRemaining_big,getString(R.string.unknown));
  getNotificationCustomViewBig().setTextViewText(R.id.notification_service_textView_transferRate_big,getString(R.string.unknown));
  getNotificationManager().notify(getNotificationId(),getNotificationBuilder().build());
}

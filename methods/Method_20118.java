/** 
 * Dismiss the progress notification.
 */
protected void dismissProgressNotification(){
  NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
  manager.cancel(PROGRESS_NOTIFICATION_ID);
}

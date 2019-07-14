private void displayNotification(@Nullable Bitmap bitmap){
  final Notification notification;
  if (Build.VERSION.SDK_INT >= 26) {
    notification=new Notification.Builder(getContext(),NOTIFICATION_CHANNEL_ID).setSmallIcon(R.drawable.ic_done).setLargeIcon(bitmap).setContentTitle(getString(R.string.imagepipeline_notification_content_title)).setContentText(getString(R.string.imagepipeline_notification_content_text)).build();
  }
 else {
    notification=new NotificationCompat.Builder(getContext()).setSmallIcon(R.drawable.ic_done).setLargeIcon(bitmap).setContentTitle(getString(R.string.imagepipeline_notification_content_title)).setContentText(getString(R.string.imagepipeline_notification_content_text)).build();
  }
  final NotificationManager notificationManager=(NotificationManager)getContext().getSystemService(Context.NOTIFICATION_SERVICE);
  notificationManager.notify(NOTIFICATION_ID,notification);
}

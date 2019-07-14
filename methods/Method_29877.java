private void startForeground(CharSequence contentText){
  if (mUploadedImageUrls.isEmpty()) {
    createNotificationChannel();
  }
  String contentTitle=getContext().getString(R.string.broadcast_sending_notification_title);
  Notification notification=createNotificationBuilder(contentTitle,contentText).setOngoing(true).build();
  getService().startForeground(Notifications.Ids.SENDING_BROADCAST,notification);
}

private NotificationCompat.Builder getNotification(@NonNull String title){
  if (notification == null) {
    notification=new NotificationCompat.Builder(this,title).setSmallIcon(R.drawable.ic_sync).setProgress(0,100,true);
  }
  notification.setContentTitle(title);
  return notification;
}

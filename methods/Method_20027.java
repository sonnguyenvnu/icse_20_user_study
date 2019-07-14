@Override public void onMessageReceived(RemoteMessage remoteMessage){
  createNotificationChannel();
  if (remoteMessage.getData().size() > 0) {
    Log.d(TAG,"Message data payload: " + remoteMessage.getData());
    NotificationManagerCompat manager=NotificationManagerCompat.from(this);
    Notification notification=new NotificationCompat.Builder(this,"Messages").setContentText(remoteMessage.getData().get("text")).setContentTitle("New message").setSmallIcon(R.drawable.ic_stat_notification).build();
    manager.notify(0,notification);
  }
}

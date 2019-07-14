private NotificationCompat.Builder getNotification(@NonNull String title,@NonNull String message,@NonNull String channelName){
  return new NotificationCompat.Builder(this,channelName).setContentTitle(title).setContentText(message).setAutoCancel(true);
}

private NotificationCompat.Builder createNotificationBuilder(CharSequence contentTitle,CharSequence contentText){
  Context context=getContext();
  return new NotificationCompat.Builder(context,Notifications.Channels.SEND_BROADCAST.ID).setColor(ContextCompat.getColor(context,R.color.douya_primary)).setSmallIcon(R.drawable.notification_icon).setContentTitle(contentTitle).setContentText(contentText).setTicker(contentText);
}

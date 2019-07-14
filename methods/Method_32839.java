private void setupNotificationChannel(){
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)   return;
  String channelName="Termux";
  String channelDescription="Notifications from Termux";
  int importance=NotificationManager.IMPORTANCE_LOW;
  NotificationChannel channel=new NotificationChannel(NOTIFICATION_CHANNEL_ID,channelName,importance);
  channel.setDescription(channelDescription);
  NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
  manager.createNotificationChannel(channel);
}

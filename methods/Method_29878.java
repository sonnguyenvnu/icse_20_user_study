private void createNotificationChannel(){
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
    return;
  }
  Context context=getContext();
  String channelName=context.getString(Notifications.Channels.SEND_BROADCAST.NAME_RES);
  @SuppressLint("WrongConstant") NotificationChannel channel=new NotificationChannel(Notifications.Channels.SEND_BROADCAST.ID,channelName,Notifications.Channels.SEND_BROADCAST.IMPORTANCE);
  String channelDescription=context.getString(Notifications.Channels.SEND_BROADCAST.DESCRIPTION_RES);
  channel.setDescription(channelDescription);
  NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
  notificationManager.createNotificationChannel(channel);
}

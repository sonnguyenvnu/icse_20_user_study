/** 
 * You CANNOT call this from android < O. THis channel is set so it doesn't bother the user, with the lowest importance.
 */
@RequiresApi(api=Build.VERSION_CODES.O) private static void createNormalChannel(Context context){
  NotificationManager mNotificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
  if (mNotificationManager.getNotificationChannel(CHANNEL_NORMAL_ID) == null) {
    NotificationChannel mChannel=new NotificationChannel(CHANNEL_NORMAL_ID,context.getString(R.string.channelname_normal),NotificationManager.IMPORTANCE_MIN);
    mChannel.setDescription(context.getString(R.string.channeldescription_normal));
    mNotificationManager.createNotificationChannel(mChannel);
  }
}

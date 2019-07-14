/** 
 * You CANNOT call this from android < O. THis channel is set so it doesn't bother the user, but it has importance.
 */
@RequiresApi(api=Build.VERSION_CODES.O) private static void createFtpChannel(Context context){
  NotificationManager mNotificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
  if (mNotificationManager.getNotificationChannel(CHANNEL_FTP_ID) == null) {
    NotificationChannel mChannel=new NotificationChannel(CHANNEL_FTP_ID,context.getString(R.string.channelname_ftp),NotificationManager.IMPORTANCE_HIGH);
    mChannel.setDescription(context.getString(R.string.channeldescription_ftp));
    mNotificationManager.createNotificationChannel(mChannel);
  }
}

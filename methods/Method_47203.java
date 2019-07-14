private void createNotification(Context context,boolean noStopButton){
  String notificationService=Context.NOTIFICATION_SERVICE;
  NotificationManager notificationManager=(NotificationManager)context.getSystemService(notificationService);
  SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
  int port=sharedPreferences.getInt(FtpService.PORT_PREFERENCE_KEY,FtpService.DEFAULT_PORT);
  boolean secureConnection=sharedPreferences.getBoolean(FtpService.KEY_PREFERENCE_SECURE,FtpService.DEFAULT_SECURE);
  InetAddress address=FtpService.getLocalInetAddress(context);
  String iptext=(secureConnection ? FtpService.INITIALS_HOST_SFTP : FtpService.INITIALS_HOST_FTP) + address.getHostAddress() + ":" + port + "/";
  int icon=R.drawable.ic_ftp_light;
  CharSequence tickerText=context.getString(R.string.ftp_notif_starting);
  long when=System.currentTimeMillis();
  CharSequence contentTitle=context.getString(R.string.ftp_notif_title);
  CharSequence contentText=String.format(context.getString(R.string.ftp_notif_text),iptext);
  Intent notificationIntent=new Intent(context,MainActivity.class);
  notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
  PendingIntent contentIntent=PendingIntent.getActivity(context,0,notificationIntent,0);
  NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(context,NotificationConstants.CHANNEL_FTP_ID).setContentTitle(contentTitle).setContentText(contentText).setContentIntent(contentIntent).setSmallIcon(icon).setTicker(tickerText).setWhen(when).setOngoing(true);
  NotificationConstants.setMetadata(context,notificationBuilder,NotificationConstants.TYPE_FTP);
  Notification notification;
  if (!noStopButton && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    int stopIcon=android.R.drawable.ic_menu_close_clear_cancel;
    CharSequence stopText=context.getString(R.string.ftp_notif_stop_server);
    Intent stopIntent=new Intent(FtpService.ACTION_STOP_FTPSERVER).setPackage(context.getPackageName());
    PendingIntent stopPendingIntent=PendingIntent.getBroadcast(context,0,stopIntent,PendingIntent.FLAG_ONE_SHOT);
    notificationBuilder.addAction(stopIcon,stopText,stopPendingIntent);
    notificationBuilder.setShowWhen(false);
    notification=notificationBuilder.build();
  }
 else {
    notification=notificationBuilder.getNotification();
  }
  notificationManager.notify(NotificationConstants.FTP_ID,notification);
}

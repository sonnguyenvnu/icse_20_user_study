/** 
 * Displays a notification in the notification area of the UI
 * @param context Context from which to create the notification
 * @param messageString The string to display to the user as a message
 * @param intent The intent which will start the activity when the user clicks the notification
 * @param notificationTitle The resource reference to the notification title
 */
static void notifcation(Context context,String messageString,Intent intent,int notificationTitle){
  String ns=Context.NOTIFICATION_SERVICE;
  NotificationManager mNotificationManager=(NotificationManager)context.getSystemService(ns);
  long when=System.currentTimeMillis();
  CharSequence contentTitle=context.getString(notificationTitle);
  String ticker=contentTitle + " " + messageString;
  PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);
  Builder notificationCompat=new Builder(context);
  notificationCompat.setAutoCancel(true).setContentTitle(contentTitle).setContentIntent(pendingIntent).setContentText(messageString).setTicker(ticker).setWhen(when).setSmallIcon(R.mipmap.ic_launcher);
  Notification notification=notificationCompat.build();
  mNotificationManager.notify(MessageID,notification);
  MessageID++;
}

private void removeNotification(Context context){
  String ns=Context.NOTIFICATION_SERVICE;
  NotificationManager nm=(NotificationManager)context.getSystemService(ns);
  nm.cancelAll();
}

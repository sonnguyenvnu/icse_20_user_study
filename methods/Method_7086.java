public static void checkOtherNotificationsChannel(){
  if (Build.VERSION.SDK_INT < 26) {
    return;
  }
  SharedPreferences preferences=null;
  if (OTHER_NOTIFICATIONS_CHANNEL == null) {
    preferences=ApplicationLoader.applicationContext.getSharedPreferences("Notifications",Activity.MODE_PRIVATE);
    OTHER_NOTIFICATIONS_CHANNEL=preferences.getString("OtherKey","Other3");
  }
  NotificationChannel notificationChannel=systemNotificationManager.getNotificationChannel(OTHER_NOTIFICATIONS_CHANNEL);
  if (notificationChannel != null && notificationChannel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
    systemNotificationManager.deleteNotificationChannel(OTHER_NOTIFICATIONS_CHANNEL);
    OTHER_NOTIFICATIONS_CHANNEL=null;
    notificationChannel=null;
  }
  if (OTHER_NOTIFICATIONS_CHANNEL == null) {
    if (preferences == null) {
      preferences=ApplicationLoader.applicationContext.getSharedPreferences("Notifications",Activity.MODE_PRIVATE);
    }
    OTHER_NOTIFICATIONS_CHANNEL="Other" + Utilities.random.nextLong();
    preferences.edit().putString("OtherKey",OTHER_NOTIFICATIONS_CHANNEL).commit();
  }
  if (notificationChannel == null) {
    notificationChannel=new NotificationChannel(OTHER_NOTIFICATIONS_CHANNEL,"Other",NotificationManager.IMPORTANCE_DEFAULT);
    notificationChannel.enableLights(false);
    notificationChannel.enableVibration(false);
    notificationChannel.setSound(null,null);
    systemNotificationManager.createNotificationChannel(notificationChannel);
  }
}

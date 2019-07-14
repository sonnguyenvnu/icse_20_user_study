public static void startPushService(){
  SharedPreferences preferences=MessagesController.getGlobalNotificationsSettings();
  if (preferences.getBoolean("pushService",true)) {
    try {
      applicationContext.startService(new Intent(applicationContext,NotificationsService.class));
    }
 catch (    Throwable ignore) {
    }
  }
 else {
    stopPushService();
  }
}

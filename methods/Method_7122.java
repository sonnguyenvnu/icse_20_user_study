public void onDestroy(){
  SharedPreferences preferences=MessagesController.getGlobalNotificationsSettings();
  if (preferences.getBoolean("pushService",true)) {
    Intent intent=new Intent("org.telegram.start");
    sendBroadcast(intent);
  }
}

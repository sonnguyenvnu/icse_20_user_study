public static void onProxyError(){
  AndroidUtilities.runOnUIThread(() -> NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needShowAlert,3));
}

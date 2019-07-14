public static int getNotificationTaskDuration(){
  if (PrefHelper.isExist("notificationEnabled") && PrefHelper.getBoolean("notificationEnabled")) {
    String prefValue=PrefHelper.getString("notificationTime");
    if (prefValue != null) {
      return notificationDurationMillis(prefValue);
    }
  }
  return -1;
}

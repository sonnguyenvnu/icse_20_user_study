@SuppressWarnings("BooleanMethodIsAlwaysInverted") public static boolean isMarkAsReadEnabled(){
  return PrefHelper.getBoolean("markNotificationAsRead");
}

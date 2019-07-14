public static boolean hasNotifications(){
  return App.getInstance().getDataStore().count(FastHubNotification.class).get().value() > 0;
}

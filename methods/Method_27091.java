public static boolean hasUnreadNotifications(){
  return App.getInstance().getDataStore().toBlocking().count(Notification.class).where(Notification.UNREAD.equal(true)).get().value() > 0;
}

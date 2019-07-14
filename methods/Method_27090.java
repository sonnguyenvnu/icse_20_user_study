public static Single<List<Notification>> getUnreadNotifications(){
  return App.getInstance().getDataStore().select(Notification.class).where(Notification.UNREAD.eq(true)).orderBy(Notification.UPDATED_AT.desc()).get().observable().toList();
}

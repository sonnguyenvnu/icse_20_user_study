@Nonnull public static Observable<FastHubNotification> getNotifications(){
  return App.getInstance().getDataStore().select(FastHubNotification.class).orderBy(FastHubNotification.DATE.desc()).get().observable();
}

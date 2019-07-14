@Nullable public static FastHubNotification getLatest(){
  return App.getInstance().getDataStore().toBlocking().select(FastHubNotification.class).where(FastHubNotification.READ.eq(false)).orderBy(FastHubNotification.DATE.desc()).limit(1).get().firstOrNull();
}

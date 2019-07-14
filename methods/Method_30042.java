private void onLoadFromCacheFinished(NotificationCount notificationCount){
  mLoadingFromCache=false;
  if (mStopped) {
    return;
  }
  boolean hasCache=notificationCount != null;
  if (hasCache) {
    setAndNotifyListener(notificationCount);
  }
  if (!hasCache || Settings.AUTO_REFRESH_HOME.getValue()) {
    mHandler.post(() -> {
      if (mStopped) {
        return;
      }
      NotificationCountResource.super.onLoadOnStart();
    }
);
  }
}

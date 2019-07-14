private void onLoadFromCacheFinished(List<Notification> notificationList){
  mLoadingFromCache=false;
  if (mStopped) {
    return;
  }
  boolean hasCache=notificationList != null && !notificationList.isEmpty();
  if (hasCache) {
    setAndNotifyListener(notificationList,true);
  }
  if (!hasCache || Settings.AUTO_REFRESH_HOME.getValue()) {
    mHandler.post(() -> {
      if (mStopped) {
        return;
      }
      NotificationListResource.super.onLoadOnStart();
    }
);
  }
}

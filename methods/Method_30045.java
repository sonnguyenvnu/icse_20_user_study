protected void setAndNotifyListener(NotificationCount notificationCount){
  set(notificationCount);
  getListener().onLoadNotificationCountFinished(getRequestCode());
  getListener().onNotificationCountChanged(getRequestCode(),notificationCount);
}

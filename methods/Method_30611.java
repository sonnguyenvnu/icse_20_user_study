protected void setAndNotifyListener(List<Notification> notificationList,boolean notifyFinished){
  if (has()) {
    for (    Notification notification : get()) {
      if (!notification.read) {
        for (        Notification newNotification : notificationList) {
          if (newNotification.id == notification.id) {
            newNotification.read=false;
            break;
          }
        }
      }
    }
  }
  set(notificationList);
  if (notifyFinished) {
    getListener().onLoadNotificationListFinished(getRequestCode());
  }
  getListener().onNotificationListChanged(getRequestCode(),Collections.unmodifiableList(notificationList));
}

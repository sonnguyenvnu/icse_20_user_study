@Subscribe(threadMode=ThreadMode.POSTING) public void onNotificationDeleted(NotificationDeletedEvent event){
  if (event.isFromMyself(this) || isEmpty()) {
    return;
  }
  List<Notification> notificationList=get();
  for (int i=0, size=notificationList.size(); i < size; ) {
    Notification notification=notificationList.get(i);
    if (notification.id == event.notificationId) {
      notificationList.remove(i);
      getListener().onNotificationRemoved(getRequestCode(),i);
      --size;
    }
 else {
      ++i;
    }
  }
}

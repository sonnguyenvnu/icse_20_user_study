@Subscribe(threadMode=ThreadMode.POSTING) public void onNotificationUpdated(NotificationUpdatedEvent event){
  if (event.isFromMyself(this) || isEmpty()) {
    return;
  }
  List<Notification> notificationList=get();
  for (int i=0, size=notificationList.size(); i < size; ++i) {
    Notification notification=notificationList.get(i);
    if (notification.id == event.notification.id) {
      notificationList.set(i,event.notification);
      getListener().onNotificationChanged(getRequestCode(),i,notificationList.get(i));
    }
  }
}

public int getUnreadCount(){
  if (!mNotificationListResource.has()) {
    return 0;
  }
  int count=0;
  for (  Notification notification : mNotificationListResource.get()) {
    if (!notification.read) {
      ++count;
    }
  }
  return count;
}

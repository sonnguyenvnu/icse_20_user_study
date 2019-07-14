@Override public void onMarkNotificationAsRead(Notification notification){
  notification.read=true;
  EventBusUtils.postAsync(new NotificationUpdatedEvent(notification,this));
}

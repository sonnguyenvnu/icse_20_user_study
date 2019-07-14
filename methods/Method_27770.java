@Override public void onReadNotification(@NonNull Notification notification){
  GroupedNotificationModel model=new GroupedNotificationModel(notification);
  if (onNotificationChangedListener != null)   onNotificationChangedListener.onNotificationChanged(model,1);
  adapter.removeItem(model);
  ReadNotificationService.start(getContext(),notification.getId());
  invalidateMenu();
}

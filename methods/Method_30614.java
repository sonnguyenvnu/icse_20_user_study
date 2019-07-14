@Override public void onNotificationListChanged(int requestCode,List<Notification> newNotificationList){
  mNotificationAdapter.replace(newNotificationList);
  onNotificationListUpdated();
}

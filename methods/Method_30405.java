public void onNotificationUnreadCountUpdate(int count){
  if (mNotificationMenuItem != null) {
    ActionItemBadge.update(mNotificationMenuItem,count);
  }
}

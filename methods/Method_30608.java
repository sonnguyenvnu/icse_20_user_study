@Subscribe(threadMode=ThreadMode.POSTING) public void onNotificationListUpdated(NotificationListUpdatedEvent event){
  if (event.isFromMyself(this) || mAccount == null) {
    return;
  }
  if (event.account.equals(mAccount)) {
    setAndNotifyListener(event.notificationList,false);
  }
}

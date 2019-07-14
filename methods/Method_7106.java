public void showNotifications(){
  notificationsQueue.postRunnable(() -> showOrUpdateNotification(false));
}

public void updateBadge(){
  notificationsQueue.postRunnable(() -> setBadge(getTotalAllUnreadCount()));
}

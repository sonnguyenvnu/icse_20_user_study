void resumeNotifications(){
  if (!notificationsPaused) {
    throw new IllegalStateException("Notifications already resumed");
  }
  notificationsPaused=false;
}

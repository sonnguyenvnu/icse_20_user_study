void pauseNotifications(){
  if (notificationsPaused) {
    throw new IllegalStateException("Notifications already paused");
  }
  notificationsPaused=true;
}

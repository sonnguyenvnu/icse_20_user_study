public void cancel(@NonNull Habit habit){
  int notificationId=getNotificationId(habit);
  systemTray.removeNotification(notificationId);
  active.remove(habit);
}

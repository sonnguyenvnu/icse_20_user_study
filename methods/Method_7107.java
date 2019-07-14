public void hideNotifications(){
  notificationsQueue.postRunnable(() -> {
    notificationManager.cancel(notificationId);
    lastWearNotifiedMessageId.clear();
    for (int a=0; a < wearNotificationsIds.size(); a++) {
      notificationManager.cancel(wearNotificationsIds.valueAt(a));
    }
    wearNotificationsIds.clear();
  }
);
}

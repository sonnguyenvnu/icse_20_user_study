protected void repeatNotificationMaybe(){
  notificationsQueue.postRunnable(() -> {
    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    if (hour >= 11 && hour <= 22) {
      notificationManager.cancel(notificationId);
      showOrUpdateNotification(true);
    }
 else {
      scheduleNotificationRepeat();
    }
  }
);
}

private void scheduleNotificationDelay(boolean onlineReason){
  try {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("delay notification start, onlineReason = " + onlineReason);
    }
    notificationDelayWakelock.acquire(10000);
    notificationsQueue.cancelRunnable(notificationDelayRunnable);
    notificationsQueue.postRunnable(notificationDelayRunnable,(onlineReason ? 3 * 1000 : 1000));
  }
 catch (  Exception e) {
    FileLog.e(e);
    showOrUpdateNotification(notifyCheck);
  }
}

/** 
 * Update the shown foreground service notification after making any changes that affect it. 
 */
void updateNotification(){
  if (mWakeLock == null && mTerminalSessions.isEmpty() && mBackgroundTasks.isEmpty()) {
    stopSelf();
  }
 else {
    ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE)).notify(NOTIFICATION_ID,buildNotification());
  }
}

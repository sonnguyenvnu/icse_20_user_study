private void cancelNotification(){
  if (mForegroundStarted) {
    mService.stopForeground(true);
  }
 else {
    getNotificationManager().cancel(mNotificationId);
  }
}

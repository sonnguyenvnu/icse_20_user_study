private void postNotification(){
  if (!mChannelCreated) {
    createNotificationChannel();
    mChannelCreated=true;
  }
  boolean isPlaying=mIsPlaying.getAsBoolean();
  if (!isPlaying && mForegroundStarted) {
    mService.stopForeground(false);
    mForegroundStarted=false;
  }
  Notification notification=buildNotification();
  if (isPlaying) {
    mService.startForeground(mNotificationId,notification);
    mForegroundStarted=true;
  }
 else {
    getNotificationManager().notify(mNotificationId,notification);
  }
}

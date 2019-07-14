public void cancel(){
  stopProgressTimer();
  if (videoPlayer != null) {
    videoPlayer.releasePlayer(true);
    videoPlayer=null;
  }
  if (textureView == null) {
    return;
  }
  cancelled=true;
  recording=false;
  AndroidUtilities.cancelRunOnUIThread(timerRunnable);
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.recordStopped,0);
  if (cameraThread != null) {
    saveLastCameraBitmap();
    cameraThread.shutdown(0);
    cameraThread=null;
  }
  if (cameraFile != null) {
    cameraFile.delete();
    cameraFile=null;
  }
  startAnimation(false);
}

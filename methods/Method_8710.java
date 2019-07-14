public void send(int state){
  if (textureView == null) {
    return;
  }
  stopProgressTimer();
  if (videoPlayer != null) {
    videoPlayer.releasePlayer(true);
    videoPlayer=null;
  }
  if (state == 4) {
    if (videoEditedInfo.needConvert()) {
      file=null;
      encryptedFile=null;
      key=null;
      iv=null;
      double totalDuration=videoEditedInfo.estimatedDuration;
      long startTime=videoEditedInfo.startTime >= 0 ? videoEditedInfo.startTime : 0;
      long endTime=videoEditedInfo.endTime >= 0 ? videoEditedInfo.endTime : videoEditedInfo.estimatedDuration;
      videoEditedInfo.estimatedDuration=endTime - startTime;
      videoEditedInfo.estimatedSize=Math.max(1,(long)(size * (videoEditedInfo.estimatedDuration / totalDuration)));
      videoEditedInfo.bitrate=400000;
      if (videoEditedInfo.startTime > 0) {
        videoEditedInfo.startTime*=1000;
      }
      if (videoEditedInfo.endTime > 0) {
        videoEditedInfo.endTime*=1000;
      }
      FileLoader.getInstance(currentAccount).cancelUploadFile(cameraFile.getAbsolutePath(),false);
    }
 else {
      videoEditedInfo.estimatedSize=Math.max(1,size);
    }
    videoEditedInfo.file=file;
    videoEditedInfo.encryptedFile=encryptedFile;
    videoEditedInfo.key=key;
    videoEditedInfo.iv=iv;
    baseFragment.sendMedia(new MediaController.PhotoEntry(0,0,0,cameraFile.getAbsolutePath(),0,true),videoEditedInfo);
  }
 else {
    cancelled=recordedTime < 800;
    recording=false;
    AndroidUtilities.cancelRunOnUIThread(timerRunnable);
    if (cameraThread != null) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.recordStopped,!cancelled && state == 3 ? 2 : 0);
      int send;
      if (cancelled) {
        send=0;
      }
 else       if (state == 3) {
        send=2;
      }
 else {
        send=1;
      }
      saveLastCameraBitmap();
      cameraThread.shutdown(send);
      cameraThread=null;
    }
    if (cancelled) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.audioRecordTooShort,true);
      startAnimation(false);
    }
  }
}

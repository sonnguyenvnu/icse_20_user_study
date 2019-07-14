public void updateButtonState(boolean ifSame,boolean animated){
  String fileName=currentMessageObject.getFileName();
  if (TextUtils.isEmpty(fileName)) {
    return;
  }
  boolean fileExists=currentMessageObject.attachPathExists || currentMessageObject.mediaExists;
  if (SharedConfig.streamMedia && currentMessageObject.isMusic() && (int)currentMessageObject.getDialogId() != 0) {
    hasMiniProgress=fileExists ? 1 : 2;
    fileExists=true;
  }
 else {
    hasMiniProgress=0;
    miniButtonState=-1;
  }
  if (hasMiniProgress != 0) {
    radialProgress.setMiniProgressBackgroundColor(Theme.getColor(currentMessageObject.isOutOwner() ? Theme.key_chat_outLoader : Theme.key_chat_inLoader));
    boolean playing=MediaController.getInstance().isPlayingMessage(currentMessageObject);
    if (!playing || playing && MediaController.getInstance().isMessagePaused()) {
      buttonState=0;
    }
 else {
      buttonState=1;
    }
    radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
    if (hasMiniProgress == 1) {
      DownloadController.getInstance(currentAccount).removeLoadingFileObserver(this);
      miniButtonState=-1;
      radialProgress.setMiniIcon(getMiniIconForCurrentState(),ifSame,animated);
    }
 else {
      DownloadController.getInstance(currentAccount).addLoadingFileObserver(fileName,currentMessageObject,this);
      if (!FileLoader.getInstance(currentAccount).isLoadingFile(fileName)) {
        miniButtonState=0;
        radialProgress.setMiniIcon(getMiniIconForCurrentState(),ifSame,animated);
      }
 else {
        miniButtonState=1;
        radialProgress.setMiniIcon(getMiniIconForCurrentState(),ifSame,animated);
        Float progress=ImageLoader.getInstance().getFileProgress(fileName);
        if (progress != null) {
          radialProgress.setProgress(progress,animated);
        }
 else {
          radialProgress.setProgress(0,animated);
        }
      }
    }
  }
 else   if (fileExists) {
    DownloadController.getInstance(currentAccount).removeLoadingFileObserver(this);
    boolean playing=MediaController.getInstance().isPlayingMessage(currentMessageObject);
    if (!playing || playing && MediaController.getInstance().isMessagePaused()) {
      buttonState=0;
    }
 else {
      buttonState=1;
    }
    radialProgress.setProgress(1,animated);
    radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
    invalidate();
  }
 else {
    DownloadController.getInstance(currentAccount).addLoadingFileObserver(fileName,currentMessageObject,this);
    boolean isLoading=FileLoader.getInstance(currentAccount).isLoadingFile(fileName);
    if (!isLoading) {
      buttonState=2;
      radialProgress.setProgress(0,animated);
      radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
    }
 else {
      buttonState=4;
      Float progress=ImageLoader.getInstance().getFileProgress(fileName);
      if (progress != null) {
        radialProgress.setProgress(progress,animated);
      }
 else {
        radialProgress.setProgress(0,animated);
      }
      radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
    }
    invalidate();
  }
}

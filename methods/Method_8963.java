public void updateButtonState(){
  String fileName=currentMessageObject.getFileName();
  File cacheFile=FileLoader.getPathToMessage(currentMessageObject.messageOwner);
  if (cacheFile.exists()) {
    DownloadController.getInstance(currentAccount).removeLoadingFileObserver(this);
    boolean playing=MediaController.getInstance().isPlayingMessage(currentMessageObject);
    if (!playing || playing && MediaController.getInstance().isMessagePaused()) {
      buttonState=0;
    }
 else {
      buttonState=1;
    }
    progressView.setProgress(0);
  }
 else {
    DownloadController.getInstance(currentAccount).addLoadingFileObserver(fileName,this);
    if (!FileLoader.getInstance(currentAccount).isLoadingFile(fileName)) {
      buttonState=2;
      progressView.setProgress(0);
    }
 else {
      buttonState=3;
      Float progress=ImageLoader.getInstance().getFileProgress(fileName);
      if (progress != null) {
        progressView.setProgress(progress);
      }
 else {
        progressView.setProgress(0);
      }
    }
  }
  updateProgress();
}

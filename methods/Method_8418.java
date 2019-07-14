private void checkIfMusicDownloaded(MessageObject messageObject){
  File cacheFile=null;
  if (messageObject.messageOwner.attachPath != null && messageObject.messageOwner.attachPath.length() > 0) {
    cacheFile=new File(messageObject.messageOwner.attachPath);
    if (!cacheFile.exists()) {
      cacheFile=null;
    }
  }
  if (cacheFile == null) {
    cacheFile=FileLoader.getPathToMessage(messageObject.messageOwner);
  }
  boolean canStream=SharedConfig.streamMedia && (int)messageObject.getDialogId() != 0 && messageObject.isMusic();
  if (!cacheFile.exists() && !canStream) {
    String fileName=messageObject.getFileName();
    DownloadController.getInstance(currentAccount).addLoadingFileObserver(fileName,this);
    Float progress=ImageLoader.getInstance().getFileProgress(fileName);
    progressView.setProgress(progress != null ? progress : 0,false);
    progressView.setVisibility(View.VISIBLE);
    seekBarView.setVisibility(View.INVISIBLE);
    playButton.setEnabled(false);
  }
 else {
    DownloadController.getInstance(currentAccount).removeLoadingFileObserver(this);
    progressView.setVisibility(View.INVISIBLE);
    seekBarView.setVisibility(View.VISIBLE);
    playButton.setEnabled(true);
  }
}

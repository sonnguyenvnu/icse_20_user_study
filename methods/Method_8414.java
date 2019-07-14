@Override public void dismiss(){
  super.dismiss();
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagePlayingDidReset);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagePlayingPlayStateChanged);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagePlayingDidStart);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagePlayingProgressDidChanged);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.musicDidLoad);
  DownloadController.getInstance(currentAccount).removeLoadingFileObserver(this);
}

private void removeObservers(){
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.fileDidFailedLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.fileDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.FileLoadProgressChanged);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.mediaCountDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.mediaDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.dialogPhotosLoaded);
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.emojiDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.filePreparingFailed);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.fileNewChunkAvailable);
  ConnectionsManager.getInstance(currentAccount).cancelRequestsForGuid(classGuid);
}

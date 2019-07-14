public void onDestroy(){
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.albumsDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.reloadInlineHints);
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.cameraInitied);
  baseFragment=null;
}

public void onDestroy(){
  if (stickersGridAdapter != null) {
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.stickersDidLoad);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.recentDocumentsDidLoad);
  }
}

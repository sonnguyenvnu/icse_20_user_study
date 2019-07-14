@Override public void setVisibility(int visibility){
  super.setVisibility(visibility);
  if (visibility == GONE) {
    NotificationCenter.getInstance(accountNum).removeObserver(this,NotificationCenter.fileDidLoad);
    NotificationCenter.getInstance(accountNum).removeObserver(this,NotificationCenter.fileDidFailedLoad);
    NotificationCenter.getInstance(accountNum).removeObserver(this,NotificationCenter.FileLoadProgressChanged);
  }
}

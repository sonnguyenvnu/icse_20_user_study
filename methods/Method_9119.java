@Override public void dismiss(){
  super.dismiss();
  NotificationCenter.getInstance(accountNum).removeObserver(this,NotificationCenter.fileDidLoad);
  NotificationCenter.getInstance(accountNum).removeObserver(this,NotificationCenter.fileDidFailedLoad);
  NotificationCenter.getInstance(accountNum).removeObserver(this,NotificationCenter.FileLoadProgressChanged);
}

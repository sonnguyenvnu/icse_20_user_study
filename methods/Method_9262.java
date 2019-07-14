@Override public void onFragmentDestroy(){
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.chatInfoDidLoad);
}

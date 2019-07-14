@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.dialogsNeedReload) {
    if (listAdapter != null) {
      listAdapter.fetchDialogs();
    }
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.dialogsNeedReload);
  }
}

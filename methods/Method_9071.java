@Override public void dismiss(){
  super.dismiss();
  if (reqId != 0) {
    ConnectionsManager.getInstance(currentAccount).cancelRequest(reqId,true);
    reqId=0;
  }
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.emojiDidLoad);
}

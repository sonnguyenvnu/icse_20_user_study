protected void onFinish(){
  if (finished) {
    return;
  }
  finished=true;
  if (isReply) {
    popupMessages.clear();
  }
  for (int a=0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
    NotificationCenter.getInstance(a).removeObserver(this,NotificationCenter.appDidLogout);
    NotificationCenter.getInstance(a).removeObserver(this,NotificationCenter.updateInterfaces);
    NotificationCenter.getInstance(a).removeObserver(this,NotificationCenter.messagePlayingProgressDidChanged);
    NotificationCenter.getInstance(a).removeObserver(this,NotificationCenter.messagePlayingDidReset);
    NotificationCenter.getInstance(a).removeObserver(this,NotificationCenter.contactsDidLoad);
  }
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.pushMessagesUpdated);
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.emojiDidLoad);
  if (chatActivityEnterView != null) {
    chatActivityEnterView.onDestroy();
  }
  if (wakeLock.isHeld()) {
    wakeLock.release();
  }
}

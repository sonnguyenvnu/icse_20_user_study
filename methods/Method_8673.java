@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.liveLocationsChanged) {
    checkLiveLocation(false);
  }
 else   if (id == NotificationCenter.liveLocationsCacheChanged) {
    if (fragment instanceof ChatActivity) {
      long did=(Long)args[0];
      if (((ChatActivity)fragment).getDialogId() == did) {
        checkLocationString();
      }
    }
  }
 else   if (id == NotificationCenter.messagePlayingDidStart || id == NotificationCenter.messagePlayingPlayStateChanged || id == NotificationCenter.messagePlayingDidReset || id == NotificationCenter.didEndedCall) {
    checkPlayer(false);
  }
 else   if (id == NotificationCenter.didStartedCall) {
    checkCall(false);
  }
 else {
    checkPlayer(false);
  }
}

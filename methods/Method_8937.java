@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.emojiDidLoad) {
    if (emojiView != null) {
      emojiView.invalidateViews();
    }
  }
}

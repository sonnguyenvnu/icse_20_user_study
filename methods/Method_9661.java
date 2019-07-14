@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.emojiDidLoad) {
    for (    ImageView iv : keyEmojiViews) {
      iv.invalidate();
    }
  }
  if (id == NotificationCenter.closeInCallActivity) {
    finish();
  }
}

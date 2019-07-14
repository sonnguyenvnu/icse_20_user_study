public void onDestroy(){
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.emojiDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.newEmojiSuggestionsAvailable);
  if (stickersGridAdapter != null) {
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.stickersDidLoad);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.recentDocumentsDidLoad);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.featuredStickersDidLoad);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.groupStickersDidLoad);
  }
}

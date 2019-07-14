public void onDestroy(){
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.newEmojiSuggestionsAvailable);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.fileDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.fileDidFailedLoad);
}

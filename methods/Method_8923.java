public void onCreate(){
  NotificationCenter.getGlobalInstance().addObserver(this,NotificationCenter.emojiDidLoad);
  sizeNotifierLayout.setDelegate(this);
}

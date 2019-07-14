@Override public boolean onFragmentCreate(){
  currentChat=MessagesController.getInstance(currentAccount).getChat(chatId);
  if (currentChat == null) {
    final CountDownLatch countDownLatch=new CountDownLatch(1);
    MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
      currentChat=MessagesStorage.getInstance(currentAccount).getChat(chatId);
      countDownLatch.countDown();
    }
);
    try {
      countDownLatch.await();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    if (currentChat != null) {
      MessagesController.getInstance(currentAccount).putChat(currentChat,true);
    }
 else {
      return false;
    }
    if (info == null) {
      MessagesStorage.getInstance(currentAccount).loadChatInfo(chatId,countDownLatch,false,false);
      try {
        countDownLatch.await();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      if (info == null) {
        return false;
      }
    }
  }
  isChannel=ChatObject.isChannel(currentChat) && !currentChat.megagroup;
  imageUpdater.parentFragment=this;
  imageUpdater.delegate=this;
  signMessages=currentChat.signatures;
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.chatInfoDidLoad);
  return super.onFragmentCreate();
}

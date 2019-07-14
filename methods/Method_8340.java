@SuppressWarnings("unchecked") @Override public boolean onFragmentCreate(){
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
  isPrivate=TextUtils.isEmpty(currentChat.username);
  isChannel=ChatObject.isChannel(currentChat) && !currentChat.megagroup;
  if (isPrivate && currentChat.creator) {
    TLRPC.TL_channels_checkUsername req=new TLRPC.TL_channels_checkUsername();
    req.username="1";
    req.channel=new TLRPC.TL_inputChannelEmpty();
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      canCreatePublic=error == null || !error.text.equals("CHANNELS_ADMIN_PUBLIC_TOO_MUCH");
      if (!canCreatePublic) {
        loadAdminedChannels();
      }
    }
));
  }
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.chatInfoDidLoad);
  return super.onFragmentCreate();
}

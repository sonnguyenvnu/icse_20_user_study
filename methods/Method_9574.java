@Override public boolean onFragmentCreate(){
  user_id=arguments.getInt("user_id",0);
  chat_id=arguments.getInt("chat_id",0);
  banFromGroup=arguments.getInt("ban_chat_id",0);
  if (user_id != 0) {
    dialog_id=arguments.getLong("dialog_id",0);
    if (dialog_id != 0) {
      currentEncryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat((int)(dialog_id >> 32));
    }
    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(user_id);
    if (user == null) {
      return false;
    }
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.updateInterfaces);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.contactsDidLoad);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.encryptedChatCreated);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.encryptedChatUpdated);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.blockedUsersDidLoad);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.botInfoDidLoad);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.userInfoDidLoad);
    userBlocked=MessagesController.getInstance(currentAccount).blockedUsers.indexOfKey(user_id) >= 0;
    if (user.bot) {
      isBot=true;
      DataQuery.getInstance(currentAccount).loadBotInfo(user.id,true,classGuid);
    }
    userInfo=MessagesController.getInstance(currentAccount).getUserFull(user_id);
    MessagesController.getInstance(currentAccount).loadFullUser(MessagesController.getInstance(currentAccount).getUser(user_id),classGuid,true);
    participantsMap=null;
  }
 else   if (chat_id != 0) {
    currentChat=MessagesController.getInstance(currentAccount).getChat(chat_id);
    if (currentChat == null) {
      final CountDownLatch countDownLatch=new CountDownLatch(1);
      MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
        currentChat=MessagesStorage.getInstance(currentAccount).getChat(chat_id);
        countDownLatch.countDown();
      }
);
      try {
        countDownLatch.await();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      if (currentChat != null) {
        MessagesController.getInstance(currentAccount).putChat(currentChat,true);
      }
 else {
        return false;
      }
    }
    if (currentChat.megagroup) {
      getChannelParticipants(true);
    }
 else {
      participantsMap=null;
    }
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.chatInfoDidLoad);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.chatOnlineCountDidLoad);
    sortedUsers=new ArrayList<>();
    updateOnlineCount();
    if (ChatObject.isChannel(currentChat)) {
      MessagesController.getInstance(currentAccount).loadFullChat(chat_id,classGuid,true);
    }
 else     if (chatInfo == null) {
      MessagesController.getInstance(currentAccount).loadChatInfo(chat_id,null,false);
    }
    if (chatInfo == null) {
      chatInfo=getMessagesController().getChatFull(chat_id);
    }
  }
 else {
    return false;
  }
  sharedMediaData=new MediaActivity.SharedMediaData[5];
  for (int a=0; a < sharedMediaData.length; a++) {
    sharedMediaData[a]=new MediaActivity.SharedMediaData();
    sharedMediaData[a].setMaxId(0,dialog_id != 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE);
  }
  loadMediaCounts();
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.mediaCountDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.mediaCountsDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.mediaDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.updateInterfaces);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.didReceiveNewMessages);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagesDeleted);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.closeChats);
  updateRowsIds();
  return true;
}

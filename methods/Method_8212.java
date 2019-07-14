@Override public boolean onFragmentCreate(){
  final int chatId=arguments.getInt("chat_id",0);
  final int userId=arguments.getInt("user_id",0);
  final int encId=arguments.getInt("enc_id",0);
  inlineReturn=arguments.getLong("inline_return",0);
  String inlineQuery=arguments.getString("inline_query");
  startLoadFromMessageId=arguments.getInt("message_id",0);
  int migrated_to=arguments.getInt("migrated_to",0);
  scrollToTopOnResume=arguments.getBoolean("scrollToTopOnResume",false);
  if (chatId != 0) {
    currentChat=MessagesController.getInstance(currentAccount).getChat(chatId);
    if (currentChat == null) {
      final CountDownLatch countDownLatch=new CountDownLatch(1);
      final MessagesStorage messagesStorage=MessagesStorage.getInstance(currentAccount);
      messagesStorage.getStorageQueue().postRunnable(() -> {
        currentChat=messagesStorage.getChat(chatId);
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
    if (chatId > 0) {
      dialog_id=-chatId;
    }
 else {
      isBroadcast=true;
      dialog_id=AndroidUtilities.makeBroadcastId(chatId);
    }
    if (ChatObject.isChannel(currentChat)) {
      MessagesController.getInstance(currentAccount).startShortPoll(currentChat,false);
    }
  }
 else   if (userId != 0) {
    currentUser=MessagesController.getInstance(currentAccount).getUser(userId);
    if (currentUser == null) {
      final MessagesStorage messagesStorage=MessagesStorage.getInstance(currentAccount);
      final CountDownLatch countDownLatch=new CountDownLatch(1);
      messagesStorage.getStorageQueue().postRunnable(() -> {
        currentUser=messagesStorage.getUser(userId);
        countDownLatch.countDown();
      }
);
      try {
        countDownLatch.await();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      if (currentUser != null) {
        MessagesController.getInstance(currentAccount).putUser(currentUser,true);
      }
 else {
        return false;
      }
    }
    dialog_id=userId;
    botUser=arguments.getString("botUser");
    if (inlineQuery != null) {
      MessagesController.getInstance(currentAccount).sendBotStart(currentUser,inlineQuery);
    }
  }
 else   if (encId != 0) {
    currentEncryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat(encId);
    final MessagesStorage messagesStorage=MessagesStorage.getInstance(currentAccount);
    if (currentEncryptedChat == null) {
      final CountDownLatch countDownLatch=new CountDownLatch(1);
      messagesStorage.getStorageQueue().postRunnable(() -> {
        currentEncryptedChat=messagesStorage.getEncryptedChat(encId);
        countDownLatch.countDown();
      }
);
      try {
        countDownLatch.await();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      if (currentEncryptedChat != null) {
        MessagesController.getInstance(currentAccount).putEncryptedChat(currentEncryptedChat,true);
      }
 else {
        return false;
      }
    }
    currentUser=MessagesController.getInstance(currentAccount).getUser(currentEncryptedChat.user_id);
    if (currentUser == null) {
      final CountDownLatch countDownLatch=new CountDownLatch(1);
      messagesStorage.getStorageQueue().postRunnable(() -> {
        currentUser=messagesStorage.getUser(currentEncryptedChat.user_id);
        countDownLatch.countDown();
      }
);
      try {
        countDownLatch.await();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      if (currentUser != null) {
        MessagesController.getInstance(currentAccount).putUser(currentUser,true);
      }
 else {
        return false;
      }
    }
    dialog_id=((long)encId) << 32;
    maxMessageId[0]=maxMessageId[1]=Integer.MIN_VALUE;
    minMessageId[0]=minMessageId[1]=Integer.MAX_VALUE;
  }
 else {
    return false;
  }
  if (currentUser != null) {
    MediaController.getInstance().startMediaObserver();
  }
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagesDidLoad);
  NotificationCenter.getGlobalInstance().addObserver(this,NotificationCenter.emojiDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.didUpdateConnectionState);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.updateInterfaces);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.didReceiveNewMessages);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.closeChats);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagesRead);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagesDeleted);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.historyCleared);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messageReceivedByServer);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messageReceivedByAck);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messageSendError);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.chatInfoDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.contactsDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.encryptedChatUpdated);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagesReadEncrypted);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.removeAllMessagesFromDialog);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagePlayingProgressDidChanged);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagePlayingDidReset);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagePlayingGoingToStop);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagePlayingPlayStateChanged);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.screenshotTook);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.blockedUsersDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.fileNewChunkAvailable);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.didCreatedNewDeleteTask);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagePlayingDidStart);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.updateMessageMedia);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.replaceMessagesObjects);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.notificationsSettingsUpdated);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.replyMessagesDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.didReceivedWebpages);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.didReceivedWebpagesInUpdates);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagesReadContent);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.botInfoDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.botKeyboardDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.chatSearchResultsAvailable);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.chatSearchResultsLoading);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.didUpdatedMessagesViews);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.chatInfoCantLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.pinnedMessageDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.peerSettingsDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.newDraftReceived);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.userInfoDidLoad);
  NotificationCenter.getGlobalInstance().addObserver(this,NotificationCenter.didSetNewWallpapper);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.channelRightsUpdated);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.updateMentionsCount);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.audioRecordTooShort);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.didUpdatePollResults);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.chatOnlineCountDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.videoLoadingStateChanged);
  super.onFragmentCreate();
  if (currentEncryptedChat == null && !isBroadcast) {
    DataQuery.getInstance(currentAccount).loadBotKeyboard(dialog_id);
  }
  loading=true;
  MessagesController.getInstance(currentAccount).loadPeerSettings(currentUser,currentChat);
  MessagesController.getInstance(currentAccount).setLastCreatedDialogId(dialog_id,true);
  if (startLoadFromMessageId == 0) {
    SharedPreferences sharedPreferences=MessagesController.getNotificationsSettings(currentAccount);
    int messageId=sharedPreferences.getInt("diditem" + dialog_id,0);
    if (messageId != 0) {
      wasManualScroll=true;
      loadingFromOldPosition=true;
      startLoadFromMessageOffset=sharedPreferences.getInt("diditemo" + dialog_id,0);
      startLoadFromMessageId=messageId;
    }
  }
 else {
    needSelectFromMessageId=true;
  }
  if (startLoadFromMessageId != 0) {
    waitingForLoad.add(lastLoadIndex);
    if (migrated_to != 0) {
      mergeDialogId=migrated_to;
      MessagesController.getInstance(currentAccount).loadMessages(mergeDialogId,loadingFromOldPosition ? 50 : (AndroidUtilities.isTablet() ? 30 : 20),startLoadFromMessageId,0,true,0,classGuid,3,0,ChatObject.isChannel(currentChat),lastLoadIndex++);
    }
 else {
      MessagesController.getInstance(currentAccount).loadMessages(dialog_id,loadingFromOldPosition ? 50 : (AndroidUtilities.isTablet() ? 30 : 20),startLoadFromMessageId,0,true,0,classGuid,3,0,ChatObject.isChannel(currentChat),lastLoadIndex++);
    }
  }
 else {
    waitingForLoad.add(lastLoadIndex);
    MessagesController.getInstance(currentAccount).loadMessages(dialog_id,AndroidUtilities.isTablet() ? 30 : 20,0,0,true,0,classGuid,2,0,ChatObject.isChannel(currentChat),lastLoadIndex++);
  }
  if (currentChat != null) {
    CountDownLatch countDownLatch=null;
    if (isBroadcast) {
      countDownLatch=new CountDownLatch(1);
    }
    MessagesController.getInstance(currentAccount).loadChatInfo(currentChat.id,countDownLatch,true);
    chatInfo=getMessagesController().getChatFull(currentChat.id);
    if (isBroadcast && countDownLatch != null) {
      try {
        countDownLatch.await();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
  }
 else   if (currentUser != null) {
    MessagesController.getInstance(currentAccount).loadUserInfo(currentUser,true,classGuid);
  }
  if (userId != 0 && currentUser.bot) {
    DataQuery.getInstance(currentAccount).loadBotInfo(userId,true,classGuid);
  }
 else   if (chatInfo instanceof TLRPC.TL_chatFull) {
    for (int a=0; a < chatInfo.participants.participants.size(); a++) {
      TLRPC.ChatParticipant participant=chatInfo.participants.participants.get(a);
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(participant.user_id);
      if (user != null && user.bot) {
        DataQuery.getInstance(currentAccount).loadBotInfo(user.id,true,classGuid);
      }
    }
  }
  if (currentUser != null) {
    userBlocked=MessagesController.getInstance(currentAccount).blockedUsers.indexOfKey(currentUser.id) >= 0;
  }
  if (AndroidUtilities.isTablet()) {
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.openedChatChanged,dialog_id,false);
  }
  if (currentEncryptedChat != null && AndroidUtilities.getMyLayerVersion(currentEncryptedChat.layer) != SecretChatHelper.CURRENT_SECRET_CHAT_LAYER) {
    SecretChatHelper.getInstance(currentAccount).sendNotifyLayerMessage(currentEncryptedChat,null);
  }
  return true;
}

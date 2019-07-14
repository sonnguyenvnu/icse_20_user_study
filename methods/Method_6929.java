public void generateJoinMessage(final int chat_id,boolean ignoreLeft){
  TLRPC.Chat chat=getChat(chat_id);
  if (chat == null || !ChatObject.isChannel(chat_id,currentAccount) || (chat.left || chat.kicked) && !ignoreLeft) {
    return;
  }
  TLRPC.TL_messageService message=new TLRPC.TL_messageService();
  message.flags=TLRPC.MESSAGE_FLAG_HAS_FROM_ID;
  message.local_id=message.id=UserConfig.getInstance(currentAccount).getNewMessageId();
  message.date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
  message.from_id=UserConfig.getInstance(currentAccount).getClientUserId();
  message.to_id=new TLRPC.TL_peerChannel();
  message.to_id.channel_id=chat_id;
  message.dialog_id=-chat_id;
  message.post=true;
  message.action=new TLRPC.TL_messageActionChatAddUser();
  message.action.users.add(UserConfig.getInstance(currentAccount).getClientUserId());
  if (chat.megagroup) {
    message.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
  }
  UserConfig.getInstance(currentAccount).saveConfig(false);
  final ArrayList<MessageObject> pushMessages=new ArrayList<>();
  final ArrayList<TLRPC.Message> messagesArr=new ArrayList<>();
  messagesArr.add(message);
  MessageObject obj=new MessageObject(currentAccount,message,true);
  pushMessages.add(obj);
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> AndroidUtilities.runOnUIThread(() -> NotificationsController.getInstance(currentAccount).processNewMessages(pushMessages,true,false,null)));
  MessagesStorage.getInstance(currentAccount).putMessages(messagesArr,true,true,false,0);
  AndroidUtilities.runOnUIThread(() -> {
    updateInterfaceWithMessages(-chat_id,pushMessages);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
  }
);
}

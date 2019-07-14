public void checkChannelInviter(final int chat_id){
  AndroidUtilities.runOnUIThread(() -> {
    final TLRPC.Chat chat=getChat(chat_id);
    if (chat == null || !ChatObject.isChannel(chat_id,currentAccount) || chat.creator) {
      return;
    }
    TLRPC.TL_channels_getParticipant req=new TLRPC.TL_channels_getParticipant();
    req.channel=getInputChannel(chat_id);
    req.user_id=new TLRPC.TL_inputUserSelf();
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      final TLRPC.TL_channels_channelParticipant res=(TLRPC.TL_channels_channelParticipant)response;
      if (res != null && res.participant instanceof TLRPC.TL_channelParticipantSelf && res.participant.inviter_id != UserConfig.getInstance(currentAccount).getClientUserId()) {
        if (chat.megagroup && MessagesStorage.getInstance(currentAccount).isMigratedChat(chat.id)) {
          return;
        }
        AndroidUtilities.runOnUIThread(() -> putUsers(res.users,false));
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,null,true,true);
        TLRPC.TL_messageService message=new TLRPC.TL_messageService();
        message.media_unread=true;
        message.unread=true;
        message.flags=TLRPC.MESSAGE_FLAG_HAS_FROM_ID;
        message.post=true;
        if (chat.megagroup) {
          message.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
        }
        message.local_id=message.id=UserConfig.getInstance(currentAccount).getNewMessageId();
        message.date=res.participant.date;
        message.action=new TLRPC.TL_messageActionChatAddUser();
        message.from_id=res.participant.inviter_id;
        message.action.users.add(UserConfig.getInstance(currentAccount).getClientUserId());
        message.to_id=new TLRPC.TL_peerChannel();
        message.to_id.channel_id=chat_id;
        message.dialog_id=-chat_id;
        UserConfig.getInstance(currentAccount).saveConfig(false);
        final ArrayList<MessageObject> pushMessages=new ArrayList<>();
        final ArrayList<TLRPC.Message> messagesArr=new ArrayList<>();
        ConcurrentHashMap<Integer,TLRPC.User> usersDict=new ConcurrentHashMap<>();
        for (int a=0; a < res.users.size(); a++) {
          TLRPC.User user=res.users.get(a);
          usersDict.put(user.id,user);
        }
        messagesArr.add(message);
        MessageObject obj=new MessageObject(currentAccount,message,usersDict,true);
        pushMessages.add(obj);
        MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> AndroidUtilities.runOnUIThread(() -> NotificationsController.getInstance(currentAccount).processNewMessages(pushMessages,true,false,null)));
        MessagesStorage.getInstance(currentAccount).putMessages(messagesArr,true,true,false,0);
        AndroidUtilities.runOnUIThread(() -> {
          updateInterfaceWithMessages(-chat_id,pushMessages);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
        }
);
      }
    }
);
  }
);
}

public void addUserToChat(final int chat_id,final TLRPC.User user,final TLRPC.ChatFull info,int count_fwd,String botHash,final BaseFragment fragment,final Runnable onFinishRunnable){
  if (user == null) {
    return;
  }
  if (chat_id > 0) {
    final TLObject request;
    final boolean isChannel=ChatObject.isChannel(chat_id,currentAccount);
    final boolean isMegagroup=isChannel && getChat(chat_id).megagroup;
    final TLRPC.InputUser inputUser=getInputUser(user);
    if (botHash == null || isChannel && !isMegagroup) {
      if (isChannel) {
        if (inputUser instanceof TLRPC.TL_inputUserSelf) {
          if (joiningToChannels.contains(chat_id)) {
            return;
          }
          TLRPC.TL_channels_joinChannel req=new TLRPC.TL_channels_joinChannel();
          req.channel=getInputChannel(chat_id);
          request=req;
          joiningToChannels.add(chat_id);
        }
 else {
          TLRPC.TL_channels_inviteToChannel req=new TLRPC.TL_channels_inviteToChannel();
          req.channel=getInputChannel(chat_id);
          req.users.add(inputUser);
          request=req;
        }
      }
 else {
        TLRPC.TL_messages_addChatUser req=new TLRPC.TL_messages_addChatUser();
        req.chat_id=chat_id;
        req.fwd_limit=count_fwd;
        req.user_id=inputUser;
        request=req;
      }
    }
 else {
      TLRPC.TL_messages_startBot req=new TLRPC.TL_messages_startBot();
      req.bot=inputUser;
      if (isChannel) {
        req.peer=getInputPeer(-chat_id);
      }
 else {
        req.peer=new TLRPC.TL_inputPeerChat();
        req.peer.chat_id=chat_id;
      }
      req.start_param=botHash;
      req.random_id=Utilities.random.nextLong();
      request=req;
    }
    ConnectionsManager.getInstance(currentAccount).sendRequest(request,(response,error) -> {
      if (isChannel && inputUser instanceof TLRPC.TL_inputUserSelf) {
        AndroidUtilities.runOnUIThread(() -> joiningToChannels.remove((Integer)chat_id));
      }
      if (error != null) {
        AndroidUtilities.runOnUIThread(() -> {
          AlertsCreator.processError(currentAccount,error,fragment,request,isChannel && !isMegagroup);
          if (isChannel && inputUser instanceof TLRPC.TL_inputUserSelf) {
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_CHAT);
          }
        }
);
        return;
      }
      boolean hasJoinMessage=false;
      TLRPC.Updates updates=(TLRPC.Updates)response;
      for (int a=0; a < updates.updates.size(); a++) {
        TLRPC.Update update=updates.updates.get(a);
        if (update instanceof TLRPC.TL_updateNewChannelMessage) {
          if (((TLRPC.TL_updateNewChannelMessage)update).message.action instanceof TLRPC.TL_messageActionChatAddUser) {
            hasJoinMessage=true;
            break;
          }
        }
      }
      processUpdates(updates,false);
      if (isChannel) {
        if (!hasJoinMessage && inputUser instanceof TLRPC.TL_inputUserSelf) {
          generateJoinMessage(chat_id,true);
        }
        AndroidUtilities.runOnUIThread(() -> loadFullChat(chat_id,0,true),1000);
      }
      if (isChannel && inputUser instanceof TLRPC.TL_inputUserSelf) {
        MessagesStorage.getInstance(currentAccount).updateDialogsWithDeletedMessages(new ArrayList<>(),null,true,chat_id);
      }
      if (onFinishRunnable != null) {
        AndroidUtilities.runOnUIThread(onFinishRunnable);
      }
    }
);
  }
 else {
    if (info instanceof TLRPC.TL_chatFull) {
      for (int a=0; a < info.participants.participants.size(); a++) {
        if (info.participants.participants.get(a).user_id == user.id) {
          return;
        }
      }
      TLRPC.Chat chat=getChat(chat_id);
      chat.participants_count++;
      ArrayList<TLRPC.Chat> chatArrayList=new ArrayList<>();
      chatArrayList.add(chat);
      MessagesStorage.getInstance(currentAccount).putUsersAndChats(null,chatArrayList,true,true);
      TLRPC.TL_chatParticipant newPart=new TLRPC.TL_chatParticipant();
      newPart.user_id=user.id;
      newPart.inviter_id=UserConfig.getInstance(currentAccount).getClientUserId();
      newPart.date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
      info.participants.participants.add(0,newPart);
      MessagesStorage.getInstance(currentAccount).updateChatInfo(info,true);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoad,info,0,false,null);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_CHAT_MEMBERS);
    }
  }
}

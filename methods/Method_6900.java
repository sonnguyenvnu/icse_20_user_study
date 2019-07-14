public int createChat(String title,ArrayList<Integer> selectedContacts,final String about,int type,final BaseFragment fragment){
  if (type == ChatObject.CHAT_TYPE_BROADCAST) {
    TLRPC.TL_chat chat=new TLRPC.TL_chat();
    chat.id=UserConfig.getInstance(currentAccount).lastBroadcastId;
    chat.title=title;
    chat.photo=new TLRPC.TL_chatPhotoEmpty();
    chat.participants_count=selectedContacts.size();
    chat.date=(int)(System.currentTimeMillis() / 1000);
    chat.version=1;
    UserConfig.getInstance(currentAccount).lastBroadcastId--;
    putChat(chat,false);
    ArrayList<TLRPC.Chat> chatsArrays=new ArrayList<>();
    chatsArrays.add(chat);
    MessagesStorage.getInstance(currentAccount).putUsersAndChats(null,chatsArrays,true,true);
    TLRPC.TL_chatFull chatFull=new TLRPC.TL_chatFull();
    chatFull.id=chat.id;
    chatFull.chat_photo=new TLRPC.TL_photoEmpty();
    chatFull.notify_settings=new TLRPC.TL_peerNotifySettingsEmpty_layer77();
    chatFull.exported_invite=new TLRPC.TL_chatInviteEmpty();
    chatFull.participants=new TLRPC.TL_chatParticipants();
    chatFull.participants.chat_id=chat.id;
    chatFull.participants.admin_id=UserConfig.getInstance(currentAccount).getClientUserId();
    chatFull.participants.version=1;
    for (int a=0; a < selectedContacts.size(); a++) {
      TLRPC.TL_chatParticipant participant=new TLRPC.TL_chatParticipant();
      participant.user_id=selectedContacts.get(a);
      participant.inviter_id=UserConfig.getInstance(currentAccount).getClientUserId();
      participant.date=(int)(System.currentTimeMillis() / 1000);
      chatFull.participants.participants.add(participant);
    }
    MessagesStorage.getInstance(currentAccount).updateChatInfo(chatFull,false);
    TLRPC.TL_messageService newMsg=new TLRPC.TL_messageService();
    newMsg.action=new TLRPC.TL_messageActionCreatedBroadcastList();
    newMsg.local_id=newMsg.id=UserConfig.getInstance(currentAccount).getNewMessageId();
    newMsg.from_id=UserConfig.getInstance(currentAccount).getClientUserId();
    newMsg.dialog_id=AndroidUtilities.makeBroadcastId(chat.id);
    newMsg.to_id=new TLRPC.TL_peerChat();
    newMsg.to_id.chat_id=chat.id;
    newMsg.date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
    newMsg.random_id=0;
    newMsg.flags|=TLRPC.MESSAGE_FLAG_HAS_FROM_ID;
    UserConfig.getInstance(currentAccount).saveConfig(false);
    MessageObject newMsgObj=new MessageObject(currentAccount,newMsg,users,true);
    newMsgObj.messageOwner.send_state=MessageObject.MESSAGE_SEND_STATE_SENT;
    ArrayList<MessageObject> objArr=new ArrayList<>();
    objArr.add(newMsgObj);
    ArrayList<TLRPC.Message> arr=new ArrayList<>();
    arr.add(newMsg);
    MessagesStorage.getInstance(currentAccount).putMessages(arr,false,true,false,0);
    updateInterfaceWithMessages(newMsg.dialog_id,objArr);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatDidCreated,chat.id);
    return 0;
  }
 else   if (type == ChatObject.CHAT_TYPE_CHAT) {
    final TLRPC.TL_messages_createChat req=new TLRPC.TL_messages_createChat();
    req.title=title;
    for (int a=0; a < selectedContacts.size(); a++) {
      TLRPC.User user=getUser(selectedContacts.get(a));
      if (user == null) {
        continue;
      }
      req.users.add(getInputUser(user));
    }
    return ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (error != null) {
        AndroidUtilities.runOnUIThread(() -> {
          AlertsCreator.processError(currentAccount,error,fragment,req);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatDidFailCreate);
        }
);
        return;
      }
      final TLRPC.Updates updates=(TLRPC.Updates)response;
      processUpdates(updates,false);
      AndroidUtilities.runOnUIThread(() -> {
        putUsers(updates.users,false);
        putChats(updates.chats,false);
        if (updates.chats != null && !updates.chats.isEmpty()) {
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatDidCreated,updates.chats.get(0).id);
        }
 else {
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatDidFailCreate);
        }
      }
);
    }
,ConnectionsManager.RequestFlagFailOnServerErrors);
  }
 else   if (type == ChatObject.CHAT_TYPE_CHANNEL || type == ChatObject.CHAT_TYPE_MEGAGROUP) {
    final TLRPC.TL_channels_createChannel req=new TLRPC.TL_channels_createChannel();
    req.title=title;
    req.about=about != null ? about : "";
    if (type == ChatObject.CHAT_TYPE_MEGAGROUP) {
      req.megagroup=true;
    }
 else {
      req.broadcast=true;
    }
    return ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (error != null) {
        AndroidUtilities.runOnUIThread(() -> {
          AlertsCreator.processError(currentAccount,error,fragment,req);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatDidFailCreate);
        }
);
        return;
      }
      final TLRPC.Updates updates=(TLRPC.Updates)response;
      processUpdates(updates,false);
      AndroidUtilities.runOnUIThread(() -> {
        putUsers(updates.users,false);
        putChats(updates.chats,false);
        if (updates.chats != null && !updates.chats.isEmpty()) {
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatDidCreated,updates.chats.get(0).id);
        }
 else {
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatDidFailCreate);
        }
      }
);
    }
,ConnectionsManager.RequestFlagFailOnServerErrors);
  }
  return 0;
}

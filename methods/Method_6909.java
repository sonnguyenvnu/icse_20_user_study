public void deleteUserFromChat(final int chat_id,final TLRPC.User user,final TLRPC.ChatFull info,boolean forceDelete,boolean revoke){
  if (user == null) {
    return;
  }
  if (chat_id > 0) {
    final TLRPC.InputUser inputUser=getInputUser(user);
    TLObject request;
    TLRPC.Chat chat=getChat(chat_id);
    final boolean isChannel=ChatObject.isChannel(chat);
    if (isChannel) {
      if (inputUser instanceof TLRPC.TL_inputUserSelf) {
        if (chat.creator && forceDelete) {
          TLRPC.TL_channels_deleteChannel req=new TLRPC.TL_channels_deleteChannel();
          req.channel=getInputChannel(chat);
          request=req;
        }
 else {
          TLRPC.TL_channels_leaveChannel req=new TLRPC.TL_channels_leaveChannel();
          req.channel=getInputChannel(chat);
          request=req;
        }
      }
 else {
        TLRPC.TL_channels_editBanned req=new TLRPC.TL_channels_editBanned();
        req.channel=getInputChannel(chat);
        req.user_id=inputUser;
        req.banned_rights=new TLRPC.TL_chatBannedRights();
        req.banned_rights.view_messages=true;
        req.banned_rights.send_media=true;
        req.banned_rights.send_messages=true;
        req.banned_rights.send_stickers=true;
        req.banned_rights.send_gifs=true;
        req.banned_rights.send_games=true;
        req.banned_rights.send_inline=true;
        req.banned_rights.embed_links=true;
        req.banned_rights.pin_messages=true;
        req.banned_rights.send_polls=true;
        req.banned_rights.invite_users=true;
        req.banned_rights.change_info=true;
        request=req;
      }
    }
 else {
      TLRPC.TL_messages_deleteChatUser req=new TLRPC.TL_messages_deleteChatUser();
      req.chat_id=chat_id;
      req.user_id=getInputUser(user);
      request=req;
    }
    if (user.id == UserConfig.getInstance(currentAccount).getClientUserId()) {
      deleteDialog(-chat_id,0,revoke);
    }
    ConnectionsManager.getInstance(currentAccount).sendRequest(request,(response,error) -> {
      if (error != null) {
        return;
      }
      final TLRPC.Updates updates=(TLRPC.Updates)response;
      processUpdates(updates,false);
      if (isChannel && !(inputUser instanceof TLRPC.TL_inputUserSelf)) {
        AndroidUtilities.runOnUIThread(() -> loadFullChat(chat_id,0,true),1000);
      }
    }
,ConnectionsManager.RequestFlagInvokeAfter);
  }
 else {
    if (info instanceof TLRPC.TL_chatFull) {
      TLRPC.Chat chat=getChat(chat_id);
      chat.participants_count--;
      ArrayList<TLRPC.Chat> chatArrayList=new ArrayList<>();
      chatArrayList.add(chat);
      MessagesStorage.getInstance(currentAccount).putUsersAndChats(null,chatArrayList,true,true);
      boolean changed=false;
      for (int a=0; a < info.participants.participants.size(); a++) {
        TLRPC.ChatParticipant p=info.participants.participants.get(a);
        if (p.user_id == user.id) {
          info.participants.participants.remove(a);
          changed=true;
          break;
        }
      }
      if (changed) {
        MessagesStorage.getInstance(currentAccount).updateChatInfo(info,true);
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoad,info,0,false,null);
      }
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_CHAT_MEMBERS);
    }
  }
}

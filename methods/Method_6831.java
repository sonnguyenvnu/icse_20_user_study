public void loadFullChat(final int chat_id,final int classGuid,boolean force){
  boolean loaded=loadedFullChats.contains(chat_id);
  if (loadingFullChats.contains(chat_id) || !force && loaded) {
    return;
  }
  loadingFullChats.add(chat_id);
  TLObject request;
  final long dialog_id=-chat_id;
  final TLRPC.Chat chat=getChat(chat_id);
  if (ChatObject.isChannel(chat)) {
    TLRPC.TL_channels_getFullChannel req=new TLRPC.TL_channels_getFullChannel();
    req.channel=getInputChannel(chat);
    request=req;
    if (chat.megagroup) {
      loadChannelAdmins(chat_id,!loaded);
    }
  }
 else {
    TLRPC.TL_messages_getFullChat req=new TLRPC.TL_messages_getFullChat();
    req.chat_id=chat_id;
    request=req;
    if (dialogs_read_inbox_max.get(dialog_id) == null || dialogs_read_outbox_max.get(dialog_id) == null) {
      reloadDialogsReadValue(null,dialog_id);
    }
  }
  int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(request,(response,error) -> {
    if (error == null) {
      final TLRPC.TL_messages_chatFull res=(TLRPC.TL_messages_chatFull)response;
      MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,true,true);
      MessagesStorage.getInstance(currentAccount).updateChatInfo(res.full_chat,false);
      if (ChatObject.isChannel(chat)) {
        Integer value=dialogs_read_inbox_max.get(dialog_id);
        if (value == null) {
          value=MessagesStorage.getInstance(currentAccount).getDialogReadMax(false,dialog_id);
        }
        dialogs_read_inbox_max.put(dialog_id,Math.max(res.full_chat.read_inbox_max_id,value));
        if (value == 0) {
          ArrayList<TLRPC.Update> arrayList=new ArrayList<>();
          TLRPC.TL_updateReadChannelInbox update=new TLRPC.TL_updateReadChannelInbox();
          update.channel_id=chat_id;
          update.max_id=res.full_chat.read_inbox_max_id;
          arrayList.add(update);
          processUpdateArray(arrayList,null,null,false);
        }
        value=dialogs_read_outbox_max.get(dialog_id);
        if (value == null) {
          value=MessagesStorage.getInstance(currentAccount).getDialogReadMax(true,dialog_id);
        }
        dialogs_read_outbox_max.put(dialog_id,Math.max(res.full_chat.read_outbox_max_id,value));
        if (value == 0) {
          ArrayList<TLRPC.Update> arrayList=new ArrayList<>();
          TLRPC.TL_updateReadChannelOutbox update=new TLRPC.TL_updateReadChannelOutbox();
          update.channel_id=chat_id;
          update.max_id=res.full_chat.read_outbox_max_id;
          arrayList.add(update);
          processUpdateArray(arrayList,null,null,false);
        }
      }
      AndroidUtilities.runOnUIThread(() -> {
        fullChats.put(chat_id,res.full_chat);
        applyDialogNotificationsSettings(-chat_id,res.full_chat.notify_settings);
        for (int a=0; a < res.full_chat.bot_info.size(); a++) {
          TLRPC.BotInfo botInfo=res.full_chat.bot_info.get(a);
          DataQuery.getInstance(currentAccount).putBotInfo(botInfo);
        }
        exportedChats.put(chat_id,res.full_chat.exported_invite);
        loadingFullChats.remove((Integer)chat_id);
        loadedFullChats.add(chat_id);
        putUsers(res.users,false);
        putChats(res.chats,false);
        if (res.full_chat.stickerset != null) {
          DataQuery.getInstance(currentAccount).getGroupStickerSetById(res.full_chat.stickerset);
        }
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoad,res.full_chat,classGuid,false,null);
      }
);
    }
 else {
      AndroidUtilities.runOnUIThread(() -> {
        checkChannelError(error.text,chat_id);
        loadingFullChats.remove((Integer)chat_id);
      }
);
    }
  }
);
  if (classGuid != 0) {
    ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
  }
}

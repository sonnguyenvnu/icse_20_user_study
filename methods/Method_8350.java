private void loadChats(){
  if (info.linked_chat_id != 0) {
    chats.clear();
    TLRPC.Chat chat=getMessagesController().getChat(info.linked_chat_id);
    if (chat != null) {
      chats.add(chat);
    }
    if (searchItem != null) {
      searchItem.setVisibility(View.GONE);
    }
  }
  if (loadingChats || !isChannel || info.linked_chat_id != 0) {
    return;
  }
  loadingChats=true;
  TLRPC.TL_channels_getGroupsForDiscussion req=new TLRPC.TL_channels_getGroupsForDiscussion();
  getConnectionsManager().sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (response instanceof TLRPC.messages_Chats) {
      TLRPC.messages_Chats res=(TLRPC.messages_Chats)response;
      getMessagesController().putChats(res.chats,false);
      chats=res.chats;
    }
    loadingChats=false;
    updateRows();
  }
));
}

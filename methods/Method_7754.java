private void searchMessagesInternal(final String query){
  if (needMessagesSearch == 0 || TextUtils.isEmpty(lastMessagesSearchString) && TextUtils.isEmpty(query)) {
    return;
  }
  if (reqId != 0) {
    ConnectionsManager.getInstance(currentAccount).cancelRequest(reqId,true);
    reqId=0;
  }
  if (TextUtils.isEmpty(query)) {
    searchResultMessages.clear();
    lastReqId=0;
    lastMessagesSearchString=null;
    searchWas=false;
    notifyDataSetChanged();
    if (delegate != null) {
      delegate.searchStateChanged(false);
    }
    return;
  }
  final TLRPC.TL_messages_searchGlobal req=new TLRPC.TL_messages_searchGlobal();
  req.limit=20;
  req.q=query;
  if (query.equals(lastMessagesSearchString) && !searchResultMessages.isEmpty()) {
    MessageObject lastMessage=searchResultMessages.get(searchResultMessages.size() - 1);
    req.offset_id=lastMessage.getId();
    req.offset_rate=nextSearchRate;
    int id;
    if (lastMessage.messageOwner.to_id.channel_id != 0) {
      id=-lastMessage.messageOwner.to_id.channel_id;
    }
 else     if (lastMessage.messageOwner.to_id.chat_id != 0) {
      id=-lastMessage.messageOwner.to_id.chat_id;
    }
 else {
      id=lastMessage.messageOwner.to_id.user_id;
    }
    req.offset_peer=MessagesController.getInstance(currentAccount).getInputPeer(id);
  }
 else {
    req.offset_rate=0;
    req.offset_id=0;
    req.offset_peer=new TLRPC.TL_inputPeerEmpty();
  }
  lastMessagesSearchString=query;
  final int currentReqId=++lastReqId;
  reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (currentReqId == lastReqId) {
      if (error == null) {
        TLRPC.messages_Messages res=(TLRPC.messages_Messages)response;
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,true,true);
        MessagesController.getInstance(currentAccount).putUsers(res.users,false);
        MessagesController.getInstance(currentAccount).putChats(res.chats,false);
        if (req.offset_id == 0) {
          searchResultMessages.clear();
        }
        nextSearchRate=res.next_rate;
        for (int a=0; a < res.messages.size(); a++) {
          TLRPC.Message message=res.messages.get(a);
          searchResultMessages.add(new MessageObject(currentAccount,message,false));
          long dialog_id=MessageObject.getDialogId(message);
          ConcurrentHashMap<Long,Integer> read_max=message.out ? MessagesController.getInstance(currentAccount).dialogs_read_outbox_max : MessagesController.getInstance(currentAccount).dialogs_read_inbox_max;
          Integer value=read_max.get(dialog_id);
          if (value == null) {
            value=MessagesStorage.getInstance(currentAccount).getDialogReadMax(message.out,dialog_id);
            read_max.put(dialog_id,value);
          }
          message.unread=value < message.id;
        }
        searchWas=true;
        messagesSearchEndReached=res.messages.size() != 20;
        notifyDataSetChanged();
      }
    }
    if (delegate != null) {
      delegate.searchStateChanged(false);
    }
    reqId=0;
  }
),ConnectionsManager.RequestFlagFailOnServerErrors);
}

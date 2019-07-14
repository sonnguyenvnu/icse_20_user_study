private boolean getRecentLocations(){
  ArrayList<TLRPC.Message> messages=LocationController.getInstance(currentAccount).locationsCache.get(messageObject.getDialogId());
  if (messages != null && messages.isEmpty()) {
    fetchRecentLocations(messages);
  }
 else {
    messages=null;
  }
  int lower_id=(int)dialogId;
  if (lower_id < 0) {
    TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-lower_id);
    if (ChatObject.isChannel(chat) && !chat.megagroup) {
      return false;
    }
  }
  TLRPC.TL_messages_getRecentLocations req=new TLRPC.TL_messages_getRecentLocations();
  final long dialog_id=messageObject.getDialogId();
  req.peer=MessagesController.getInstance(currentAccount).getInputPeer((int)dialog_id);
  req.limit=100;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (response != null) {
      AndroidUtilities.runOnUIThread(() -> {
        if (googleMap == null) {
          return;
        }
        TLRPC.messages_Messages res=(TLRPC.messages_Messages)response;
        for (int a=0; a < res.messages.size(); a++) {
          if (!(res.messages.get(a).media instanceof TLRPC.TL_messageMediaGeoLive)) {
            res.messages.remove(a);
            a--;
          }
        }
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,true,true);
        MessagesController.getInstance(currentAccount).putUsers(res.users,false);
        MessagesController.getInstance(currentAccount).putChats(res.chats,false);
        LocationController.getInstance(currentAccount).locationsCache.put(dialog_id,res.messages);
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.liveLocationsCacheChanged,dialog_id);
        fetchRecentLocations(res.messages);
      }
);
    }
  }
);
  return messages != null;
}

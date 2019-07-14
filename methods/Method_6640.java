public void loadLiveLocations(final long did){
  if (cacheRequests.indexOfKey(did) >= 0) {
    return;
  }
  cacheRequests.put(did,true);
  TLRPC.TL_messages_getRecentLocations req=new TLRPC.TL_messages_getRecentLocations();
  req.peer=MessagesController.getInstance(currentAccount).getInputPeer((int)did);
  req.limit=100;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error != null) {
      return;
    }
    AndroidUtilities.runOnUIThread(() -> {
      cacheRequests.delete(did);
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
      locationsCache.put(did,res.messages);
      NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsCacheChanged,did,currentAccount);
    }
);
  }
);
}

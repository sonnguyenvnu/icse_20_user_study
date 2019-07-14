public void loadDialogPhotos(final int did,final int count,final long max_id,final boolean fromCache,final int classGuid){
  if (fromCache) {
    MessagesStorage.getInstance(currentAccount).getDialogPhotos(did,count,max_id,classGuid);
  }
 else {
    if (did > 0) {
      TLRPC.User user=getUser(did);
      if (user == null) {
        return;
      }
      TLRPC.TL_photos_getUserPhotos req=new TLRPC.TL_photos_getUserPhotos();
      req.limit=count;
      req.offset=0;
      req.max_id=(int)max_id;
      req.user_id=getInputUser(user);
      int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
        if (error == null) {
          TLRPC.photos_Photos res=(TLRPC.photos_Photos)response;
          processLoadedUserPhotos(res,did,count,max_id,false,classGuid);
        }
      }
);
      ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
    }
 else     if (did < 0) {
      TLRPC.TL_messages_search req=new TLRPC.TL_messages_search();
      req.filter=new TLRPC.TL_inputMessagesFilterChatPhotos();
      req.limit=count;
      req.offset_id=(int)max_id;
      req.q="";
      req.peer=getInputPeer(did);
      int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
        if (error == null) {
          TLRPC.messages_Messages messages=(TLRPC.messages_Messages)response;
          TLRPC.TL_photos_photos res=new TLRPC.TL_photos_photos();
          res.count=messages.count;
          res.users.addAll(messages.users);
          for (int a=0; a < messages.messages.size(); a++) {
            TLRPC.Message message=messages.messages.get(a);
            if (message.action == null || message.action.photo == null) {
              continue;
            }
            res.photos.add(message.action.photo);
          }
          processLoadedUserPhotos(res,did,count,max_id,false,classGuid);
        }
      }
);
      ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
    }
  }
}

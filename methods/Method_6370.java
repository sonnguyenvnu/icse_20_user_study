public void getMediaCount(final long uid,final int type,final int classGuid,boolean fromCache){
  int lower_part=(int)uid;
  if (fromCache || lower_part == 0) {
    getMediaCountDatabase(uid,type,classGuid);
  }
 else {
    TLRPC.TL_messages_search req=new TLRPC.TL_messages_search();
    req.limit=1;
    req.offset_id=0;
    if (type == MEDIA_PHOTOVIDEO) {
      req.filter=new TLRPC.TL_inputMessagesFilterPhotoVideo();
    }
 else     if (type == MEDIA_FILE) {
      req.filter=new TLRPC.TL_inputMessagesFilterDocument();
    }
 else     if (type == MEDIA_AUDIO) {
      req.filter=new TLRPC.TL_inputMessagesFilterRoundVoice();
    }
 else     if (type == MEDIA_URL) {
      req.filter=new TLRPC.TL_inputMessagesFilterUrl();
    }
 else     if (type == MEDIA_MUSIC) {
      req.filter=new TLRPC.TL_inputMessagesFilterMusic();
    }
    req.q="";
    req.peer=MessagesController.getInstance(currentAccount).getInputPeer(lower_part);
    if (req.peer == null) {
      return;
    }
    int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (error == null) {
        final TLRPC.messages_Messages res=(TLRPC.messages_Messages)response;
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,true,true);
        int count;
        if (res instanceof TLRPC.TL_messages_messages) {
          count=res.messages.size();
        }
 else {
          count=res.count;
        }
        AndroidUtilities.runOnUIThread(() -> {
          MessagesController.getInstance(currentAccount).putUsers(res.users,false);
          MessagesController.getInstance(currentAccount).putChats(res.chats,false);
        }
);
        processLoadedMediaCount(count,uid,type,classGuid,false,0);
      }
    }
);
    ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
  }
}

public void loadMedia(final long uid,final int count,final int max_id,final int type,final int fromCache,final int classGuid){
  final boolean isChannel=(int)uid < 0 && ChatObject.isChannel(-(int)uid,currentAccount);
  int lower_part=(int)uid;
  if (fromCache != 0 || lower_part == 0) {
    loadMediaDatabase(uid,count,max_id,type,classGuid,isChannel,fromCache);
  }
 else {
    TLRPC.TL_messages_search req=new TLRPC.TL_messages_search();
    req.limit=count;
    req.offset_id=max_id;
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
        MessagesController.getInstance(currentAccount).removeDeletedMessagesFromArray(uid,res.messages);
        processLoadedMedia(res,uid,count,max_id,type,0,classGuid,isChannel,res.messages.size() == 0);
      }
    }
);
    ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
  }
}

@SuppressWarnings("unchecked") private void sendErrorToObject(Object[] args,int reason){
  if (args[0] instanceof TLRPC.TL_inputSingleMedia) {
    TLRPC.TL_messages_sendMultiMedia req=(TLRPC.TL_messages_sendMultiMedia)args[1];
    Object[] objects=multiMediaCache.get(req);
    if (objects != null) {
      multiMediaCache.remove(req);
      SendMessagesHelper.getInstance(currentAccount).performSendMessageRequestMulti(req,(ArrayList<MessageObject>)objects[1],(ArrayList<String>)objects[2],null,(SendMessagesHelper.DelayedMessage)objects[4]);
    }
  }
 else   if (args[0] instanceof TLRPC.TL_messages_sendMedia || args[0] instanceof TLRPC.TL_messages_editMessage) {
    SendMessagesHelper.getInstance(currentAccount).performSendMessageRequest((TLObject)args[0],(MessageObject)args[1],(String)args[2],(SendMessagesHelper.DelayedMessage)args[3],(Boolean)args[4],(SendMessagesHelper.DelayedMessage)args[5],null);
  }
 else   if (args[0] instanceof TLRPC.TL_messages_saveGif) {
    TLRPC.TL_messages_saveGif req=(TLRPC.TL_messages_saveGif)args[0];
  }
 else   if (args[0] instanceof TLRPC.TL_messages_saveRecentSticker) {
    TLRPC.TL_messages_saveRecentSticker req=(TLRPC.TL_messages_saveRecentSticker)args[0];
  }
 else   if (args[0] instanceof TLRPC.TL_messages_faveSticker) {
    TLRPC.TL_messages_faveSticker req=(TLRPC.TL_messages_faveSticker)args[0];
  }
 else   if (args[0] instanceof TLRPC.TL_messages_getAttachedStickers) {
    TLRPC.TL_messages_getAttachedStickers req=(TLRPC.TL_messages_getAttachedStickers)args[0];
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(RequestDelegate)args[1]);
  }
 else {
    if (reason == 0) {
      TLRPC.TL_error error=new TLRPC.TL_error();
      error.text="not found parent object to request reference";
      error.code=400;
      if (args[1] instanceof FileLoadOperation) {
        FileLoadOperation fileLoadOperation=(FileLoadOperation)args[1];
        fileLoadOperation.requestingReference=false;
        fileLoadOperation.processRequestResult((FileLoadOperation.RequestInfo)args[2],error);
      }
    }
 else     if (reason == 1) {
      if (args[1] instanceof FileLoadOperation) {
        FileLoadOperation fileLoadOperation=(FileLoadOperation)args[1];
        fileLoadOperation.requestingReference=false;
        fileLoadOperation.onFail(false,0);
      }
    }
  }
}

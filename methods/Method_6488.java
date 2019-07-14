@SuppressWarnings("unchecked") private void onUpdateObjectReference(Requester requester,byte[] file_reference,TLRPC.InputFileLocation locationReplacement){
  if (BuildVars.DEBUG_VERSION) {
    FileLog.d("fileref updated for " + requester.args[0] + " " + requester.locationKey);
  }
  if (requester.args[0] instanceof TLRPC.TL_inputSingleMedia) {
    TLRPC.TL_messages_sendMultiMedia multiMedia=(TLRPC.TL_messages_sendMultiMedia)requester.args[1];
    Object[] objects=multiMediaCache.get(multiMedia);
    if (objects == null) {
      return;
    }
    TLRPC.TL_inputSingleMedia req=(TLRPC.TL_inputSingleMedia)requester.args[0];
    if (req.media instanceof TLRPC.TL_inputMediaDocument) {
      TLRPC.TL_inputMediaDocument mediaDocument=(TLRPC.TL_inputMediaDocument)req.media;
      mediaDocument.id.file_reference=file_reference;
    }
 else     if (req.media instanceof TLRPC.TL_inputMediaPhoto) {
      TLRPC.TL_inputMediaPhoto mediaPhoto=(TLRPC.TL_inputMediaPhoto)req.media;
      mediaPhoto.id.file_reference=file_reference;
    }
    int index=multiMedia.multi_media.indexOf(req);
    if (index < 0) {
      return;
    }
    ArrayList<Object> parentObjects=(ArrayList<Object>)objects[3];
    parentObjects.set(index,null);
    boolean done=true;
    for (int a=0, size; a < parentObjects.size(); a++) {
      if (parentObjects.get(a) != null) {
        done=false;
      }
    }
    if (done) {
      multiMediaCache.remove(multiMedia);
      SendMessagesHelper.getInstance(currentAccount).performSendMessageRequestMulti(multiMedia,(ArrayList<MessageObject>)objects[1],(ArrayList<String>)objects[2],null,(SendMessagesHelper.DelayedMessage)objects[4]);
    }
  }
 else   if (requester.args[0] instanceof TLRPC.TL_messages_sendMedia) {
    TLRPC.TL_messages_sendMedia req=(TLRPC.TL_messages_sendMedia)requester.args[0];
    if (req.media instanceof TLRPC.TL_inputMediaDocument) {
      TLRPC.TL_inputMediaDocument mediaDocument=(TLRPC.TL_inputMediaDocument)req.media;
      mediaDocument.id.file_reference=file_reference;
    }
 else     if (req.media instanceof TLRPC.TL_inputMediaPhoto) {
      TLRPC.TL_inputMediaPhoto mediaPhoto=(TLRPC.TL_inputMediaPhoto)req.media;
      mediaPhoto.id.file_reference=file_reference;
    }
    SendMessagesHelper.getInstance(currentAccount).performSendMessageRequest((TLObject)requester.args[0],(MessageObject)requester.args[1],(String)requester.args[2],(SendMessagesHelper.DelayedMessage)requester.args[3],(Boolean)requester.args[4],(SendMessagesHelper.DelayedMessage)requester.args[5],null);
  }
 else   if (requester.args[0] instanceof TLRPC.TL_messages_editMessage) {
    TLRPC.TL_messages_editMessage req=(TLRPC.TL_messages_editMessage)requester.args[0];
    if (req.media instanceof TLRPC.TL_inputMediaDocument) {
      TLRPC.TL_inputMediaDocument mediaDocument=(TLRPC.TL_inputMediaDocument)req.media;
      mediaDocument.id.file_reference=file_reference;
    }
 else     if (req.media instanceof TLRPC.TL_inputMediaPhoto) {
      TLRPC.TL_inputMediaPhoto mediaPhoto=(TLRPC.TL_inputMediaPhoto)req.media;
      mediaPhoto.id.file_reference=file_reference;
    }
    SendMessagesHelper.getInstance(currentAccount).performSendMessageRequest((TLObject)requester.args[0],(MessageObject)requester.args[1],(String)requester.args[2],(SendMessagesHelper.DelayedMessage)requester.args[3],(Boolean)requester.args[4],(SendMessagesHelper.DelayedMessage)requester.args[5],null);
  }
 else   if (requester.args[0] instanceof TLRPC.TL_messages_saveGif) {
    TLRPC.TL_messages_saveGif req=(TLRPC.TL_messages_saveGif)requester.args[0];
    req.id.file_reference=file_reference;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    }
);
  }
 else   if (requester.args[0] instanceof TLRPC.TL_messages_saveRecentSticker) {
    TLRPC.TL_messages_saveRecentSticker req=(TLRPC.TL_messages_saveRecentSticker)requester.args[0];
    req.id.file_reference=file_reference;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    }
);
  }
 else   if (requester.args[0] instanceof TLRPC.TL_messages_faveSticker) {
    TLRPC.TL_messages_faveSticker req=(TLRPC.TL_messages_faveSticker)requester.args[0];
    req.id.file_reference=file_reference;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    }
);
  }
 else   if (requester.args[0] instanceof TLRPC.TL_messages_getAttachedStickers) {
    TLRPC.TL_messages_getAttachedStickers req=(TLRPC.TL_messages_getAttachedStickers)requester.args[0];
    if (req.media instanceof TLRPC.TL_inputStickeredMediaDocument) {
      TLRPC.TL_inputStickeredMediaDocument mediaDocument=(TLRPC.TL_inputStickeredMediaDocument)req.media;
      mediaDocument.id.file_reference=file_reference;
    }
 else     if (req.media instanceof TLRPC.TL_inputStickeredMediaPhoto) {
      TLRPC.TL_inputStickeredMediaPhoto mediaPhoto=(TLRPC.TL_inputStickeredMediaPhoto)req.media;
      mediaPhoto.id.file_reference=file_reference;
    }
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(RequestDelegate)requester.args[1]);
  }
 else   if (requester.args[1] instanceof FileLoadOperation) {
    FileLoadOperation fileLoadOperation=(FileLoadOperation)requester.args[1];
    if (locationReplacement != null) {
      fileLoadOperation.location=locationReplacement;
    }
 else {
      requester.location.file_reference=file_reference;
    }
    fileLoadOperation.requestingReference=false;
    fileLoadOperation.startDownloadRequest();
  }
}

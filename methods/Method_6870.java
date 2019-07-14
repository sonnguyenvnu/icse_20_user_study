public void sendTyping(final long dialog_id,final int action,int classGuid){
  if (dialog_id == 0) {
    return;
  }
  LongSparseArray<Boolean> typings=sendingTypings.get(action);
  if (typings != null && typings.get(dialog_id) != null) {
    return;
  }
  if (typings == null) {
    typings=new LongSparseArray<>();
    sendingTypings.put(action,typings);
  }
  int lower_part=(int)dialog_id;
  int high_id=(int)(dialog_id >> 32);
  if (lower_part != 0) {
    if (high_id == 1) {
      return;
    }
    TLRPC.TL_messages_setTyping req=new TLRPC.TL_messages_setTyping();
    req.peer=getInputPeer(lower_part);
    if (req.peer instanceof TLRPC.TL_inputPeerChannel) {
      TLRPC.Chat chat=getChat(req.peer.channel_id);
      if (chat == null || !chat.megagroup) {
        return;
      }
    }
    if (req.peer == null) {
      return;
    }
    if (action == 0) {
      req.action=new TLRPC.TL_sendMessageTypingAction();
    }
 else     if (action == 1) {
      req.action=new TLRPC.TL_sendMessageRecordAudioAction();
    }
 else     if (action == 2) {
      req.action=new TLRPC.TL_sendMessageCancelAction();
    }
 else     if (action == 3) {
      req.action=new TLRPC.TL_sendMessageUploadDocumentAction();
    }
 else     if (action == 4) {
      req.action=new TLRPC.TL_sendMessageUploadPhotoAction();
    }
 else     if (action == 5) {
      req.action=new TLRPC.TL_sendMessageUploadVideoAction();
    }
 else     if (action == 6) {
      req.action=new TLRPC.TL_sendMessageGamePlayAction();
    }
 else     if (action == 7) {
      req.action=new TLRPC.TL_sendMessageRecordRoundAction();
    }
 else     if (action == 8) {
      req.action=new TLRPC.TL_sendMessageUploadRoundAction();
    }
 else     if (action == 9) {
      req.action=new TLRPC.TL_sendMessageUploadAudioAction();
    }
    typings.put(dialog_id,true);
    int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      LongSparseArray<Boolean> typings1=sendingTypings.get(action);
      if (typings1 != null) {
        typings1.remove(dialog_id);
      }
    }
),ConnectionsManager.RequestFlagFailOnServerErrors);
    if (classGuid != 0) {
      ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
    }
  }
 else {
    if (action != 0) {
      return;
    }
    TLRPC.EncryptedChat chat=getEncryptedChat(high_id);
    if (chat.auth_key != null && chat.auth_key.length > 1 && chat instanceof TLRPC.TL_encryptedChat) {
      TLRPC.TL_messages_setEncryptedTyping req=new TLRPC.TL_messages_setEncryptedTyping();
      req.peer=new TLRPC.TL_inputEncryptedChat();
      req.peer.chat_id=chat.id;
      req.peer.access_hash=chat.access_hash;
      req.typing=true;
      typings.put(dialog_id,true);
      int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        LongSparseArray<Boolean> typings12=sendingTypings.get(action);
        if (typings12 != null) {
          typings12.remove(dialog_id);
        }
      }
),ConnectionsManager.RequestFlagFailOnServerErrors);
      if (classGuid != 0) {
        ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
      }
    }
  }
}

private void sendReadyToSendGroup(DelayedMessage message,boolean add,boolean check){
  if (message.messageObjects.isEmpty()) {
    message.markAsError();
    return;
  }
  String key="group_" + message.groupId;
  if (message.finalGroupMessage != message.messageObjects.get(message.messageObjects.size() - 1).getId()) {
    if (add) {
      putToDelayedMessages(key,message);
    }
    return;
  }
 else   if (add) {
    delayedMessages.remove(key);
    MessagesStorage.getInstance(currentAccount).putMessages(message.messages,false,true,false,0);
    MessagesController.getInstance(currentAccount).updateInterfaceWithMessages(message.peer,message.messageObjects);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
  }
  if (message.sendRequest instanceof TLRPC.TL_messages_sendMultiMedia) {
    TLRPC.TL_messages_sendMultiMedia request=(TLRPC.TL_messages_sendMultiMedia)message.sendRequest;
    for (int a=0; a < request.multi_media.size(); a++) {
      TLRPC.InputMedia inputMedia=request.multi_media.get(a).media;
      if (inputMedia instanceof TLRPC.TL_inputMediaUploadedPhoto || inputMedia instanceof TLRPC.TL_inputMediaUploadedDocument) {
        return;
      }
    }
    if (check) {
      DelayedMessage maxDelayedMessage=findMaxDelayedMessageForMessageId(message.finalGroupMessage,message.peer);
      if (maxDelayedMessage != null) {
        maxDelayedMessage.addDelayedRequest(message.sendRequest,message.messageObjects,message.originalPaths,message.parentObjects,message);
        if (message.requests != null) {
          maxDelayedMessage.requests.addAll(message.requests);
        }
        return;
      }
    }
  }
 else {
    TLRPC.TL_messages_sendEncryptedMultiMedia request=(TLRPC.TL_messages_sendEncryptedMultiMedia)message.sendEncryptedRequest;
    for (int a=0; a < request.files.size(); a++) {
      TLRPC.InputEncryptedFile inputMedia=request.files.get(a);
      if (inputMedia instanceof TLRPC.TL_inputEncryptedFile) {
        return;
      }
    }
  }
  if (message.sendRequest instanceof TLRPC.TL_messages_sendMultiMedia) {
    performSendMessageRequestMulti((TLRPC.TL_messages_sendMultiMedia)message.sendRequest,message.messageObjects,message.originalPaths,message.parentObjects,message);
  }
 else {
    SecretChatHelper.getInstance(currentAccount).performSendEncryptedRequest((TLRPC.TL_messages_sendEncryptedMultiMedia)message.sendEncryptedRequest,message);
  }
  message.sendDelayedRequests();
}

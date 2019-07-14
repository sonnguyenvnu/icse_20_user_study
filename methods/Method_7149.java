public void cancelSendingMessage(ArrayList<MessageObject> objects){
  ArrayList<String> keysToRemove=new ArrayList<>();
  ArrayList<Integer> messageIds=new ArrayList<>();
  boolean enc=false;
  int channelId=0;
  for (int c=0; c < objects.size(); c++) {
    MessageObject object=objects.get(c);
    messageIds.add(object.getId());
    channelId=object.messageOwner.to_id.channel_id;
    TLRPC.Message sendingMessage=removeFromSendingMessages(object.getId());
    if (sendingMessage != null) {
      ConnectionsManager.getInstance(currentAccount).cancelRequest(sendingMessage.reqId,true);
    }
    for (    HashMap.Entry<String,ArrayList<DelayedMessage>> entry : delayedMessages.entrySet()) {
      ArrayList<DelayedMessage> messages=entry.getValue();
      for (int a=0; a < messages.size(); a++) {
        DelayedMessage message=messages.get(a);
        if (message.type == 4) {
          int index=-1;
          MessageObject messageObject=null;
          for (int b=0; b < message.messageObjects.size(); b++) {
            messageObject=message.messageObjects.get(b);
            if (messageObject.getId() == object.getId()) {
              index=b;
              break;
            }
          }
          if (index >= 0) {
            message.messageObjects.remove(index);
            message.messages.remove(index);
            message.originalPaths.remove(index);
            if (message.sendRequest != null) {
              TLRPC.TL_messages_sendMultiMedia request=(TLRPC.TL_messages_sendMultiMedia)message.sendRequest;
              request.multi_media.remove(index);
            }
 else {
              TLRPC.TL_messages_sendEncryptedMultiMedia request=(TLRPC.TL_messages_sendEncryptedMultiMedia)message.sendEncryptedRequest;
              request.messages.remove(index);
              request.files.remove(index);
            }
            MediaController.getInstance().cancelVideoConvert(object);
            String keyToRemove=(String)message.extraHashMap.get(messageObject);
            if (keyToRemove != null) {
              keysToRemove.add(keyToRemove);
            }
            if (message.messageObjects.isEmpty()) {
              message.sendDelayedRequests();
            }
 else {
              if (message.finalGroupMessage == object.getId()) {
                MessageObject prevMessage=message.messageObjects.get(message.messageObjects.size() - 1);
                message.finalGroupMessage=prevMessage.getId();
                prevMessage.messageOwner.params.put("final","1");
                TLRPC.TL_messages_messages messagesRes=new TLRPC.TL_messages_messages();
                messagesRes.messages.add(prevMessage.messageOwner);
                MessagesStorage.getInstance(currentAccount).putMessages(messagesRes,message.peer,-2,0,false);
              }
              sendReadyToSendGroup(message,false,true);
            }
          }
          break;
        }
 else         if (message.obj.getId() == object.getId()) {
          messages.remove(a);
          message.sendDelayedRequests();
          MediaController.getInstance().cancelVideoConvert(message.obj);
          if (messages.size() == 0) {
            keysToRemove.add(entry.getKey());
            if (message.sendEncryptedRequest != null) {
              enc=true;
            }
          }
          break;
        }
      }
    }
  }
  for (int a=0; a < keysToRemove.size(); a++) {
    String key=keysToRemove.get(a);
    if (key.startsWith("http")) {
      ImageLoader.getInstance().cancelLoadHttpFile(key);
    }
 else {
      FileLoader.getInstance(currentAccount).cancelUploadFile(key,enc);
    }
    stopVideoService(key);
    delayedMessages.remove(key);
  }
  if (objects.size() == 1 && objects.get(0).isEditing() && objects.get(0).previousMedia != null) {
    revertEditingMessageObject(objects.get(0));
  }
 else {
    MessagesController.getInstance(currentAccount).deleteMessages(messageIds,null,null,channelId,false);
  }
}

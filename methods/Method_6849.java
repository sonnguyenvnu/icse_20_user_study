public void deleteMessages(ArrayList<Integer> messages,ArrayList<Long> randoms,TLRPC.EncryptedChat encryptedChat,final int channelId,boolean forAll,long taskId,TLObject taskRequest){
  if ((messages == null || messages.isEmpty()) && taskRequest == null) {
    return;
  }
  ArrayList<Integer> toSend=null;
  if (taskId == 0) {
    if (channelId == 0) {
      for (int a=0; a < messages.size(); a++) {
        Integer id=messages.get(a);
        MessageObject obj=dialogMessagesByIds.get(id);
        if (obj != null) {
          obj.deleted=true;
        }
      }
    }
 else {
      markChannelDialogMessageAsDeleted(messages,channelId);
    }
    toSend=new ArrayList<>();
    for (int a=0; a < messages.size(); a++) {
      Integer mid=messages.get(a);
      if (mid > 0) {
        toSend.add(mid);
      }
    }
    MessagesStorage.getInstance(currentAccount).markMessagesAsDeleted(messages,true,channelId);
    MessagesStorage.getInstance(currentAccount).updateDialogsWithDeletedMessages(messages,null,true,channelId);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messagesDeleted,messages,channelId);
  }
  final long newTaskId;
  if (channelId != 0) {
    TLRPC.TL_channels_deleteMessages req;
    if (taskRequest != null) {
      req=(TLRPC.TL_channels_deleteMessages)taskRequest;
      newTaskId=taskId;
    }
 else {
      req=new TLRPC.TL_channels_deleteMessages();
      req.id=toSend;
      req.channel=getInputChannel(channelId);
      NativeByteBuffer data=null;
      try {
        data=new NativeByteBuffer(8 + req.getObjectSize());
        data.writeInt32(7);
        data.writeInt32(channelId);
        req.serializeToStream(data);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      newTaskId=MessagesStorage.getInstance(currentAccount).createPendingTask(data);
    }
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (error == null) {
        TLRPC.TL_messages_affectedMessages res=(TLRPC.TL_messages_affectedMessages)response;
        processNewChannelDifferenceParams(res.pts,res.pts_count,channelId);
      }
      if (newTaskId != 0) {
        MessagesStorage.getInstance(currentAccount).removePendingTask(newTaskId);
      }
    }
);
  }
 else {
    if (randoms != null && encryptedChat != null && !randoms.isEmpty()) {
      SecretChatHelper.getInstance(currentAccount).sendMessagesDeleteMessage(encryptedChat,randoms,null);
    }
    TLRPC.TL_messages_deleteMessages req;
    if (taskRequest != null) {
      req=(TLRPC.TL_messages_deleteMessages)taskRequest;
      newTaskId=taskId;
    }
 else {
      req=new TLRPC.TL_messages_deleteMessages();
      req.id=toSend;
      req.revoke=forAll;
      NativeByteBuffer data=null;
      try {
        data=new NativeByteBuffer(8 + req.getObjectSize());
        data.writeInt32(7);
        data.writeInt32(channelId);
        req.serializeToStream(data);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      newTaskId=MessagesStorage.getInstance(currentAccount).createPendingTask(data);
    }
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (error == null) {
        TLRPC.TL_messages_affectedMessages res=(TLRPC.TL_messages_affectedMessages)response;
        processNewDifferenceParams(-1,res.pts,-1,res.pts_count);
      }
      if (newTaskId != 0) {
        MessagesStorage.getInstance(currentAccount).removePendingTask(newTaskId);
      }
    }
);
  }
}

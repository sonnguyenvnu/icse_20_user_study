public void markMessageAsRead(final int mid,final int channelId,TLRPC.InputChannel inputChannel,int ttl,long taskId){
  if (mid == 0 || ttl <= 0) {
    return;
  }
  if (channelId != 0 && inputChannel == null) {
    inputChannel=getInputChannel(channelId);
    if (inputChannel == null) {
      return;
    }
  }
  final long newTaskId;
  if (taskId == 0) {
    NativeByteBuffer data=null;
    try {
      data=new NativeByteBuffer(16 + (inputChannel != null ? inputChannel.getObjectSize() : 0));
      data.writeInt32(11);
      data.writeInt32(mid);
      data.writeInt32(channelId);
      data.writeInt32(ttl);
      if (channelId != 0) {
        inputChannel.serializeToStream(data);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    newTaskId=MessagesStorage.getInstance(currentAccount).createPendingTask(data);
  }
 else {
    newTaskId=taskId;
  }
  int time=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
  MessagesStorage.getInstance(currentAccount).createTaskForMid(mid,channelId,time,time,ttl,false);
  if (channelId != 0) {
    TLRPC.TL_channels_readMessageContents req=new TLRPC.TL_channels_readMessageContents();
    req.channel=inputChannel;
    req.id.add(mid);
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (newTaskId != 0) {
        MessagesStorage.getInstance(currentAccount).removePendingTask(newTaskId);
      }
    }
);
  }
 else {
    TLRPC.TL_messages_readMessageContents req=new TLRPC.TL_messages_readMessageContents();
    req.id.add(mid);
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

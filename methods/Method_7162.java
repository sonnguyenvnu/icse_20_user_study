public void sendGame(TLRPC.InputPeer peer,TLRPC.TL_inputMediaGame game,long random_id,final long taskId){
  if (peer == null || game == null) {
    return;
  }
  TLRPC.TL_messages_sendMedia request=new TLRPC.TL_messages_sendMedia();
  request.peer=peer;
  if (request.peer instanceof TLRPC.TL_inputPeerChannel) {
    request.silent=MessagesController.getNotificationsSettings(currentAccount).getBoolean("silent_" + peer.channel_id,false);
  }
  request.random_id=random_id != 0 ? random_id : getNextRandomId();
  request.message="";
  request.media=game;
  final long newTaskId;
  if (taskId == 0) {
    NativeByteBuffer data=null;
    try {
      data=new NativeByteBuffer(peer.getObjectSize() + game.getObjectSize() + 4 + 8);
      data.writeInt32(3);
      data.writeInt64(random_id);
      peer.serializeToStream(data);
      game.serializeToStream(data);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    newTaskId=MessagesStorage.getInstance(currentAccount).createPendingTask(data);
  }
 else {
    newTaskId=taskId;
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(request,(response,error) -> {
    if (error == null) {
      MessagesController.getInstance(currentAccount).processUpdates((TLRPC.Updates)response,false);
    }
    if (newTaskId != 0) {
      MessagesStorage.getInstance(currentAccount).removePendingTask(newTaskId);
    }
  }
);
}

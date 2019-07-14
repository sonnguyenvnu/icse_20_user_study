protected void loadUnknownChannel(final TLRPC.Chat channel,final long taskId){
  if (!(channel instanceof TLRPC.TL_channel) || gettingUnknownChannels.indexOfKey(channel.id) >= 0) {
    return;
  }
  if (channel.access_hash == 0) {
    if (taskId != 0) {
      MessagesStorage.getInstance(currentAccount).removePendingTask(taskId);
    }
    return;
  }
  TLRPC.TL_inputPeerChannel inputPeer=new TLRPC.TL_inputPeerChannel();
  inputPeer.channel_id=channel.id;
  inputPeer.access_hash=channel.access_hash;
  gettingUnknownChannels.put(channel.id,true);
  TLRPC.TL_messages_getPeerDialogs req=new TLRPC.TL_messages_getPeerDialogs();
  TLRPC.TL_inputDialogPeer inputDialogPeer=new TLRPC.TL_inputDialogPeer();
  inputDialogPeer.peer=inputPeer;
  req.peers.add(inputDialogPeer);
  final long newTaskId;
  if (taskId == 0) {
    NativeByteBuffer data=null;
    try {
      data=new NativeByteBuffer(4 + channel.getObjectSize());
      data.writeInt32(0);
      channel.serializeToStream(data);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    newTaskId=MessagesStorage.getInstance(currentAccount).createPendingTask(data);
  }
 else {
    newTaskId=taskId;
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (response != null) {
      TLRPC.TL_messages_peerDialogs res=(TLRPC.TL_messages_peerDialogs)response;
      if (!res.dialogs.isEmpty() && !res.chats.isEmpty()) {
        TLRPC.TL_dialog dialog=(TLRPC.TL_dialog)res.dialogs.get(0);
        TLRPC.TL_messages_dialogs dialogs=new TLRPC.TL_messages_dialogs();
        dialogs.dialogs.addAll(res.dialogs);
        dialogs.messages.addAll(res.messages);
        dialogs.users.addAll(res.users);
        dialogs.chats.addAll(res.chats);
        processLoadedDialogs(dialogs,null,dialog.folder_id,0,1,DIALOGS_LOAD_TYPE_CHANNEL,false,false,false);
      }
    }
    if (newTaskId != 0) {
      MessagesStorage.getInstance(currentAccount).removePendingTask(newTaskId);
    }
    gettingUnknownChannels.delete(channel.id);
  }
);
}

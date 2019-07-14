public void markDialogAsUnread(long did,TLRPC.InputPeer peer,long taskId){
  TLRPC.Dialog dialog=dialogs_dict.get(did);
  if (dialog != null) {
    dialog.unread_mark=true;
    if (dialog.unread_count == 0 && !isDialogMuted(did)) {
      unreadUnmutedDialogs++;
    }
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_READ_DIALOG_MESSAGE);
    MessagesStorage.getInstance(currentAccount).setDialogUnread(did,true);
  }
  int lower_id=(int)did;
  if (lower_id != 0) {
    TLRPC.TL_messages_markDialogUnread req=new TLRPC.TL_messages_markDialogUnread();
    req.unread=true;
    if (peer == null) {
      peer=getInputPeer(lower_id);
    }
    if (peer instanceof TLRPC.TL_inputPeerEmpty) {
      return;
    }
    TLRPC.TL_inputDialogPeer inputDialogPeer=new TLRPC.TL_inputDialogPeer();
    inputDialogPeer.peer=peer;
    req.peer=inputDialogPeer;
    final long newTaskId;
    if (taskId == 0) {
      NativeByteBuffer data=null;
      try {
        data=new NativeByteBuffer(12 + peer.getObjectSize());
        data.writeInt32(9);
        data.writeInt64(did);
        peer.serializeToStream(data);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      newTaskId=MessagesStorage.getInstance(currentAccount).createPendingTask(data);
    }
 else {
      newTaskId=taskId;
    }
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (newTaskId != 0) {
        MessagesStorage.getInstance(currentAccount).removePendingTask(newTaskId);
      }
    }
);
  }
}

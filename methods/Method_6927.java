public void reorderPinnedDialogs(int folderId,ArrayList<TLRPC.InputDialogPeer> order,long taskId){
  TLRPC.TL_messages_reorderPinnedDialogs req=new TLRPC.TL_messages_reorderPinnedDialogs();
  req.folder_id=folderId;
  req.force=true;
  final long newTaskId;
  if (taskId == 0) {
    ArrayList<TLRPC.Dialog> dialogs=getDialogs(folderId);
    if (dialogs.isEmpty()) {
      return;
    }
    int size=0;
    for (int a=0, N=dialogs.size(); a < N; a++) {
      TLRPC.Dialog dialog=dialogs.get(a);
      if (dialog instanceof TLRPC.TL_dialogFolder) {
        continue;
      }
      if (!dialog.pinned) {
        break;
      }
      MessagesStorage.getInstance(currentAccount).setDialogPinned(dialog.id,dialog.pinnedNum);
      if ((int)dialog.id != 0) {
        TLRPC.InputPeer inputPeer=getInputPeer((int)dialogs.get(a).id);
        TLRPC.TL_inputDialogPeer inputDialogPeer=new TLRPC.TL_inputDialogPeer();
        inputDialogPeer.peer=inputPeer;
        req.order.add(inputDialogPeer);
        size+=inputDialogPeer.getObjectSize();
      }
    }
    NativeByteBuffer data=null;
    try {
      data=new NativeByteBuffer(4 + 4 + 4 + size);
      data.writeInt32(16);
      data.writeInt32(folderId);
      data.writeInt32(req.order.size());
      for (int a=0, N=req.order.size(); a < N; a++) {
        req.order.get(a).serializeToStream(data);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    newTaskId=MessagesStorage.getInstance(currentAccount).createPendingTask(data);
  }
 else {
    req.order=order;
    newTaskId=taskId;
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (newTaskId != 0) {
      MessagesStorage.getInstance(currentAccount).removePendingTask(newTaskId);
    }
  }
);
}

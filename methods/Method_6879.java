public int addDialogToFolder(ArrayList<Long> dialogIds,int folderId,int pinnedNum,ArrayList<TLRPC.TL_inputFolderPeer> peers,long taskId){
  TLRPC.TL_folders_editPeerFolders req=new TLRPC.TL_folders_editPeerFolders();
  boolean[] folderCreated=null;
  final long newTaskId;
  if (taskId == 0) {
    boolean added=false;
    int selfUserId=UserConfig.getInstance(currentAccount).getClientUserId();
    int size=0;
    for (int a=0, N=dialogIds.size(); a < N; a++) {
      long dialogId=dialogIds.get(a);
      if (!DialogObject.isPeerDialogId(dialogId) && !DialogObject.isSecretDialogId(dialogId)) {
        continue;
      }
      if (folderId == 1 && (dialogId == selfUserId || dialogId == 777000 || isProxyDialog(dialogId,false))) {
        continue;
      }
      TLRPC.Dialog dialog=dialogs_dict.get(dialogId);
      if (dialog == null) {
        continue;
      }
      added=true;
      dialog.folder_id=folderId;
      if (pinnedNum > 0) {
        dialog.pinned=true;
        dialog.pinnedNum=pinnedNum;
      }
 else {
        dialog.pinned=false;
        dialog.pinnedNum=0;
      }
      if (folderCreated == null) {
        folderCreated=new boolean[1];
        ensureFolderDialogExists(folderId,folderCreated);
      }
      if (DialogObject.isSecretDialogId(dialogId)) {
        MessagesStorage.getInstance(currentAccount).setDialogsFolderId(null,null,dialogId,folderId);
      }
 else {
        TLRPC.TL_inputFolderPeer folderPeer=new TLRPC.TL_inputFolderPeer();
        folderPeer.folder_id=folderId;
        folderPeer.peer=getInputPeer((int)dialogId);
        req.folder_peers.add(folderPeer);
        size+=folderPeer.getObjectSize();
      }
    }
    if (!added) {
      return 0;
    }
    sortDialogs(null);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
    if (size != 0) {
      NativeByteBuffer data=null;
      try {
        data=new NativeByteBuffer(4 + 4 + 4 + size);
        data.writeInt32(17);
        data.writeInt32(folderId);
        data.writeInt32(req.folder_peers.size());
        for (int a=0, N=req.folder_peers.size(); a < N; a++) {
          req.folder_peers.get(a).serializeToStream(data);
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      newTaskId=MessagesStorage.getInstance(currentAccount).createPendingTask(data);
    }
 else {
      newTaskId=0;
    }
  }
 else {
    req.folder_peers=peers;
    newTaskId=taskId;
  }
  if (!req.folder_peers.isEmpty()) {
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (error == null) {
        processUpdates((TLRPC.Updates)response,false);
      }
      if (newTaskId != 0) {
        MessagesStorage.getInstance(currentAccount).removePendingTask(newTaskId);
      }
    }
);
    MessagesStorage.getInstance(currentAccount).setDialogsFolderId(null,req.folder_peers,0,folderId);
  }
  return folderCreated == null ? 0 : (folderCreated[0] ? 2 : 1);
}

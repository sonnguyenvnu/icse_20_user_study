public void setDialogsFolderId(final ArrayList<TLRPC.TL_folderPeer> peers,ArrayList<TLRPC.TL_inputFolderPeer> inputPeers,long dialogId,int folderId){
  if (peers == null && inputPeers == null && dialogId == 0) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      database.beginTransaction();
      SQLitePreparedStatement state=database.executeFast("UPDATE dialogs SET folder_id = ? WHERE did = ?");
      if (peers != null) {
        for (int a=0, N=peers.size(); a < N; a++) {
          TLRPC.TL_folderPeer folderPeer=peers.get(a);
          long did=DialogObject.getPeerDialogId(folderPeer.peer);
          state.requery();
          state.bindInteger(1,folderPeer.folder_id);
          state.bindLong(2,did);
          state.step();
        }
      }
 else       if (inputPeers != null) {
        for (int a=0, N=inputPeers.size(); a < N; a++) {
          TLRPC.TL_inputFolderPeer folderPeer=inputPeers.get(a);
          long did=DialogObject.getPeerDialogId(folderPeer.peer);
          state.requery();
          state.bindInteger(1,folderPeer.folder_id);
          state.bindLong(2,did);
          state.step();
        }
      }
 else {
        state.requery();
        state.bindInteger(1,folderId);
        state.bindLong(2,dialogId);
        state.step();
      }
      state.dispose();
      database.commitTransaction();
      checkIfFolderEmptyInternal(1);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}

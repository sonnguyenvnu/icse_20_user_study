private void reloadDialogsReadValue(ArrayList<TLRPC.Dialog> dialogs,long did){
  if (did == 0 && (dialogs == null || dialogs.isEmpty())) {
    return;
  }
  TLRPC.TL_messages_getPeerDialogs req=new TLRPC.TL_messages_getPeerDialogs();
  if (dialogs != null) {
    for (int a=0; a < dialogs.size(); a++) {
      TLRPC.InputPeer inputPeer=getInputPeer((int)dialogs.get(a).id);
      if (inputPeer instanceof TLRPC.TL_inputPeerChannel && inputPeer.access_hash == 0) {
        continue;
      }
      TLRPC.TL_inputDialogPeer inputDialogPeer=new TLRPC.TL_inputDialogPeer();
      inputDialogPeer.peer=inputPeer;
      req.peers.add(inputDialogPeer);
    }
  }
 else {
    TLRPC.InputPeer inputPeer=getInputPeer((int)did);
    if (inputPeer instanceof TLRPC.TL_inputPeerChannel && inputPeer.access_hash == 0) {
      return;
    }
    TLRPC.TL_inputDialogPeer inputDialogPeer=new TLRPC.TL_inputDialogPeer();
    inputDialogPeer.peer=inputPeer;
    req.peers.add(inputDialogPeer);
  }
  if (req.peers.isEmpty()) {
    return;
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (response != null) {
      TLRPC.TL_messages_peerDialogs res=(TLRPC.TL_messages_peerDialogs)response;
      ArrayList<TLRPC.Update> arrayList=new ArrayList<>();
      for (int a=0; a < res.dialogs.size(); a++) {
        TLRPC.Dialog dialog=res.dialogs.get(a);
        if (dialog.read_inbox_max_id == 0) {
          dialog.read_inbox_max_id=1;
        }
        if (dialog.read_outbox_max_id == 0) {
          dialog.read_outbox_max_id=1;
        }
        DialogObject.initDialog(dialog);
        Integer value=dialogs_read_inbox_max.get(dialog.id);
        if (value == null) {
          value=0;
        }
        dialogs_read_inbox_max.put(dialog.id,Math.max(dialog.read_inbox_max_id,value));
        if (value == 0) {
          if (dialog.peer.channel_id != 0) {
            TLRPC.TL_updateReadChannelInbox update=new TLRPC.TL_updateReadChannelInbox();
            update.channel_id=dialog.peer.channel_id;
            update.max_id=dialog.read_inbox_max_id;
            arrayList.add(update);
          }
 else {
            TLRPC.TL_updateReadHistoryInbox update=new TLRPC.TL_updateReadHistoryInbox();
            update.peer=dialog.peer;
            update.max_id=dialog.read_inbox_max_id;
            arrayList.add(update);
          }
        }
        value=dialogs_read_outbox_max.get(dialog.id);
        if (value == null) {
          value=0;
        }
        dialogs_read_outbox_max.put(dialog.id,Math.max(dialog.read_outbox_max_id,value));
        if (value == 0) {
          if (dialog.peer.channel_id != 0) {
            TLRPC.TL_updateReadChannelOutbox update=new TLRPC.TL_updateReadChannelOutbox();
            update.channel_id=dialog.peer.channel_id;
            update.max_id=dialog.read_outbox_max_id;
            arrayList.add(update);
          }
 else {
            TLRPC.TL_updateReadHistoryOutbox update=new TLRPC.TL_updateReadHistoryOutbox();
            update.peer=dialog.peer;
            update.max_id=dialog.read_outbox_max_id;
            arrayList.add(update);
          }
        }
      }
      if (!arrayList.isEmpty()) {
        processUpdateArray(arrayList,null,null,false);
      }
    }
  }
);
}

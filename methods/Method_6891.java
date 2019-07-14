protected void checkLastDialogMessage(final TLRPC.Dialog dialog,final TLRPC.InputPeer peer,long taskId){
  final int lower_id=(int)dialog.id;
  if (lower_id == 0 || checkingLastMessagesDialogs.indexOfKey(lower_id) >= 0) {
    return;
  }
  TLRPC.TL_messages_getHistory req=new TLRPC.TL_messages_getHistory();
  req.peer=peer == null ? getInputPeer(lower_id) : peer;
  if (req.peer == null) {
    return;
  }
  req.limit=1;
  checkingLastMessagesDialogs.put(lower_id,true);
  final long newTaskId;
  if (taskId == 0) {
    NativeByteBuffer data=null;
    try {
      data=new NativeByteBuffer(60 + req.peer.getObjectSize());
      data.writeInt32(14);
      data.writeInt64(dialog.id);
      data.writeInt32(dialog.top_message);
      data.writeInt32(dialog.read_inbox_max_id);
      data.writeInt32(dialog.read_outbox_max_id);
      data.writeInt32(dialog.unread_count);
      data.writeInt32(dialog.last_message_date);
      data.writeInt32(dialog.pts);
      data.writeInt32(dialog.flags);
      data.writeBool(dialog.pinned);
      data.writeInt32(dialog.pinnedNum);
      data.writeInt32(dialog.unread_mentions_count);
      data.writeBool(dialog.unread_mark);
      data.writeInt32(dialog.folder_id);
      peer.serializeToStream(data);
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
      TLRPC.messages_Messages res=(TLRPC.messages_Messages)response;
      removeDeletedMessagesFromArray(lower_id,res.messages);
      if (!res.messages.isEmpty()) {
        TLRPC.TL_messages_dialogs dialogs=new TLRPC.TL_messages_dialogs();
        TLRPC.Message newMessage=res.messages.get(0);
        TLRPC.Dialog newDialog=new TLRPC.TL_dialog();
        newDialog.flags=dialog.flags;
        newDialog.top_message=newMessage.id;
        newDialog.last_message_date=newMessage.date;
        newDialog.notify_settings=dialog.notify_settings;
        newDialog.pts=dialog.pts;
        newDialog.unread_count=dialog.unread_count;
        newDialog.unread_mark=dialog.unread_mark;
        newDialog.unread_mentions_count=dialog.unread_mentions_count;
        newDialog.read_inbox_max_id=dialog.read_inbox_max_id;
        newDialog.read_outbox_max_id=dialog.read_outbox_max_id;
        newDialog.pinned=dialog.pinned;
        newDialog.pinnedNum=dialog.pinnedNum;
        newDialog.folder_id=dialog.folder_id;
        newMessage.dialog_id=newDialog.id=dialog.id;
        dialogs.users.addAll(res.users);
        dialogs.chats.addAll(res.chats);
        dialogs.dialogs.add(newDialog);
        dialogs.messages.addAll(res.messages);
        dialogs.count=1;
        processDialogsUpdate(dialogs,null);
        MessagesStorage.getInstance(currentAccount).putMessages(res.messages,true,true,false,DownloadController.getInstance(currentAccount).getAutodownloadMask(),true);
      }
 else {
        AndroidUtilities.runOnUIThread(() -> {
          TLRPC.Dialog currentDialog=dialogs_dict.get(dialog.id);
          if (currentDialog != null && currentDialog.top_message == 0) {
            deleteDialog(dialog.id,3);
          }
        }
);
      }
    }
    if (newTaskId != 0) {
      MessagesStorage.getInstance(currentAccount).removePendingTask(newTaskId);
    }
    AndroidUtilities.runOnUIThread(() -> checkingLastMessagesDialogs.delete(lower_id));
  }
);
}

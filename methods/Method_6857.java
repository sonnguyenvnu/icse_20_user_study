protected void deleteDialog(final long did,final boolean first,final int onlyHistory,final int max_id,boolean revoke,TLRPC.InputPeer peer,final long taskId){
  if (onlyHistory == 2) {
    MessagesStorage.getInstance(currentAccount).deleteDialog(did,onlyHistory);
    return;
  }
  if (onlyHistory == 0 || onlyHistory == 3) {
    DataQuery.getInstance(currentAccount).uninstallShortcut(did);
  }
  int lower_part=(int)did;
  int high_id=(int)(did >> 32);
  int max_id_delete=max_id;
  if (first) {
    boolean isProxyDialog=false;
    MessagesStorage.getInstance(currentAccount).deleteDialog(did,onlyHistory);
    TLRPC.Dialog dialog=dialogs_dict.get(did);
    if (onlyHistory == 0 || onlyHistory == 3) {
      NotificationsController.getInstance(currentAccount).deleteNotificationChannel(did);
    }
    if (dialog != null) {
      if (max_id_delete == 0) {
        max_id_delete=Math.max(0,dialog.top_message);
        max_id_delete=Math.max(max_id_delete,dialog.read_inbox_max_id);
        max_id_delete=Math.max(max_id_delete,dialog.read_outbox_max_id);
      }
      if (onlyHistory == 0 || onlyHistory == 3) {
        if (isProxyDialog=(proxyDialog != null && proxyDialog.id == did)) {
          isLeftProxyChannel=true;
          if (proxyDialog.id < 0) {
            TLRPC.Chat chat=getChat(-(int)proxyDialog.id);
            if (chat != null) {
              chat.left=true;
            }
          }
          sortDialogs(null);
        }
 else {
          removeDialog(dialog);
          int offset=nextDialogsCacheOffset.get(dialog.folder_id,0);
          if (offset > 0) {
            nextDialogsCacheOffset.put(dialog.folder_id,offset - 1);
          }
        }
      }
 else {
        dialog.unread_count=0;
      }
      if (!isProxyDialog) {
        int lastMessageId;
        MessageObject object=dialogMessage.get(dialog.id);
        dialogMessage.remove(dialog.id);
        if (object != null) {
          lastMessageId=object.getId();
          dialogMessagesByIds.remove(object.getId());
        }
 else {
          lastMessageId=dialog.top_message;
          object=dialogMessagesByIds.get(dialog.top_message);
          dialogMessagesByIds.remove(dialog.top_message);
        }
        if (object != null && object.messageOwner.random_id != 0) {
          dialogMessagesByRandomIds.remove(object.messageOwner.random_id);
        }
        if (onlyHistory == 1 && lower_part != 0 && lastMessageId > 0) {
          TLRPC.TL_messageService message=new TLRPC.TL_messageService();
          message.id=dialog.top_message;
          message.out=UserConfig.getInstance(currentAccount).getClientUserId() == did;
          message.from_id=UserConfig.getInstance(currentAccount).getClientUserId();
          message.flags|=256;
          message.action=new TLRPC.TL_messageActionHistoryClear();
          message.date=dialog.last_message_date;
          message.dialog_id=lower_part;
          if (lower_part > 0) {
            message.to_id=new TLRPC.TL_peerUser();
            message.to_id.user_id=lower_part;
          }
 else {
            TLRPC.Chat chat=getChat(-lower_part);
            if (ChatObject.isChannel(chat)) {
              message.to_id=new TLRPC.TL_peerChannel();
              message.to_id.channel_id=-lower_part;
            }
 else {
              message.to_id=new TLRPC.TL_peerChat();
              message.to_id.chat_id=-lower_part;
            }
          }
          final MessageObject obj=new MessageObject(currentAccount,message,createdDialogIds.contains(message.dialog_id));
          final ArrayList<MessageObject> objArr=new ArrayList<>();
          objArr.add(obj);
          ArrayList<TLRPC.Message> arr=new ArrayList<>();
          arr.add(message);
          updateInterfaceWithMessages(did,objArr);
          MessagesStorage.getInstance(currentAccount).putMessages(arr,false,true,false,0);
        }
 else {
          dialog.top_message=0;
        }
      }
    }
    if (!dialogsInTransaction) {
      if (isProxyDialog) {
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload,true);
      }
 else {
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.removeAllMessagesFromDialog,did,false);
      }
    }
    MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> AndroidUtilities.runOnUIThread(() -> NotificationsController.getInstance(currentAccount).removeNotificationsForDialog(did)));
  }
  if (high_id == 1 || onlyHistory == 3) {
    return;
  }
  if (lower_part != 0) {
    if (peer == null) {
      peer=getInputPeer(lower_part);
    }
    if (peer == null) {
      return;
    }
    final long newTaskId;
    if (!(peer instanceof TLRPC.TL_inputPeerChannel) || onlyHistory != 0) {
      if (max_id_delete > 0 && max_id_delete != Integer.MAX_VALUE) {
        deletedHistory.put(did,max_id_delete);
      }
      if (taskId == 0) {
        NativeByteBuffer data=null;
        try {
          data=new NativeByteBuffer(4 + 8 + 4 + 4 + 4 + 4 + peer.getObjectSize());
          data.writeInt32(13);
          data.writeInt64(did);
          data.writeBool(first);
          data.writeInt32(onlyHistory);
          data.writeInt32(max_id_delete);
          data.writeBool(revoke);
          peer.serializeToStream(data);
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        newTaskId=MessagesStorage.getInstance(currentAccount).createPendingTask(data);
      }
 else {
        newTaskId=taskId;
      }
    }
 else {
      newTaskId=taskId;
    }
    if (peer instanceof TLRPC.TL_inputPeerChannel) {
      if (onlyHistory == 0) {
        if (newTaskId != 0) {
          MessagesStorage.getInstance(currentAccount).removePendingTask(newTaskId);
        }
        return;
      }
      TLRPC.TL_channels_deleteHistory req=new TLRPC.TL_channels_deleteHistory();
      req.channel=new TLRPC.TL_inputChannel();
      req.channel.channel_id=peer.channel_id;
      req.channel.access_hash=peer.access_hash;
      req.max_id=max_id_delete > 0 ? max_id_delete : Integer.MAX_VALUE;
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
        if (newTaskId != 0) {
          MessagesStorage.getInstance(currentAccount).removePendingTask(newTaskId);
        }
        AndroidUtilities.runOnUIThread(() -> deletedHistory.remove(did));
      }
,ConnectionsManager.RequestFlagInvokeAfter);
    }
 else {
      TLRPC.TL_messages_deleteHistory req=new TLRPC.TL_messages_deleteHistory();
      req.peer=peer;
      req.max_id=(onlyHistory == 0 ? Integer.MAX_VALUE : max_id_delete);
      req.just_clear=onlyHistory != 0;
      req.revoke=revoke;
      final int max_id_delete_final=max_id_delete;
      final TLRPC.InputPeer peerFinal=peer;
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
        if (newTaskId != 0) {
          MessagesStorage.getInstance(currentAccount).removePendingTask(newTaskId);
        }
        if (error == null) {
          TLRPC.TL_messages_affectedHistory res=(TLRPC.TL_messages_affectedHistory)response;
          if (res.offset > 0) {
            deleteDialog(did,false,onlyHistory,max_id_delete_final,revoke,peerFinal,0);
          }
          processNewDifferenceParams(-1,res.pts,-1,res.pts_count);
          MessagesStorage.getInstance(currentAccount).onDeleteQueryComplete(did);
        }
      }
,ConnectionsManager.RequestFlagInvokeAfter);
    }
  }
 else {
    if (onlyHistory == 1) {
      SecretChatHelper.getInstance(currentAccount).sendClearHistoryMessage(getEncryptedChat(high_id),null);
    }
 else {
      SecretChatHelper.getInstance(currentAccount).declineSecretChat(high_id);
    }
  }
}

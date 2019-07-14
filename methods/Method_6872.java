private void loadMessagesInternal(final long dialog_id,final int count,final int max_id,final int offset_date,final boolean fromCache,final int midDate,final int classGuid,final int load_type,final int last_message_id,final boolean isChannel,final int loadIndex,final int first_unread,final int unread_count,final int last_date,final boolean queryFromServer,final int mentionsCount,boolean loadDialog){
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("load messages in chat " + dialog_id + " count " + count + " max_id " + max_id + " cache " + fromCache + " mindate = " + midDate + " guid " + classGuid + " load_type " + load_type + " last_message_id " + last_message_id + " index " + loadIndex + " firstUnread " + first_unread + " unread_count " + unread_count + " last_date " + last_date + " queryFromServer " + queryFromServer);
  }
  int lower_part=(int)dialog_id;
  if (fromCache || lower_part == 0) {
    MessagesStorage.getInstance(currentAccount).getMessages(dialog_id,count,max_id,offset_date,midDate,classGuid,load_type,isChannel,loadIndex);
  }
 else {
    if (loadDialog && (load_type == 3 || load_type == 2) && last_message_id == 0) {
      TLRPC.TL_messages_getPeerDialogs req=new TLRPC.TL_messages_getPeerDialogs();
      TLRPC.InputPeer inputPeer=getInputPeer((int)dialog_id);
      TLRPC.TL_inputDialogPeer inputDialogPeer=new TLRPC.TL_inputDialogPeer();
      inputDialogPeer.peer=inputPeer;
      req.peers.add(inputDialogPeer);
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
        if (response != null) {
          TLRPC.TL_messages_peerDialogs res=(TLRPC.TL_messages_peerDialogs)response;
          if (!res.dialogs.isEmpty()) {
            TLRPC.Dialog dialog=res.dialogs.get(0);
            if (dialog.top_message != 0) {
              TLRPC.TL_messages_dialogs dialogs=new TLRPC.TL_messages_dialogs();
              dialogs.chats=res.chats;
              dialogs.users=res.users;
              dialogs.dialogs=res.dialogs;
              dialogs.messages=res.messages;
              MessagesStorage.getInstance(currentAccount).putDialogs(dialogs,0);
            }
            loadMessagesInternal(dialog_id,count,max_id,offset_date,false,midDate,classGuid,load_type,dialog.top_message,isChannel,loadIndex,first_unread,dialog.unread_count,last_date,queryFromServer,dialog.unread_mentions_count,false);
          }
        }
      }
);
      return;
    }
    TLRPC.TL_messages_getHistory req=new TLRPC.TL_messages_getHistory();
    req.peer=getInputPeer(lower_part);
    if (load_type == 4) {
      req.add_offset=-count + 5;
    }
 else     if (load_type == 3) {
      req.add_offset=-count / 2;
    }
 else     if (load_type == 1) {
      req.add_offset=-count - 1;
    }
 else     if (load_type == 2 && max_id != 0) {
      req.add_offset=-count + 6;
    }
 else {
      if (lower_part < 0 && max_id != 0) {
        TLRPC.Chat chat=getChat(-lower_part);
        if (ChatObject.isChannel(chat)) {
          req.add_offset=-1;
          req.limit+=1;
        }
      }
    }
    req.limit=count;
    req.offset_id=max_id;
    req.offset_date=offset_date;
    int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (response != null) {
        final TLRPC.messages_Messages res=(TLRPC.messages_Messages)response;
        removeDeletedMessagesFromArray(dialog_id,res.messages);
        if (res.messages.size() > count) {
          res.messages.remove(0);
        }
        int mid=max_id;
        if (offset_date != 0 && !res.messages.isEmpty()) {
          mid=res.messages.get(res.messages.size() - 1).id;
          for (int a=res.messages.size() - 1; a >= 0; a--) {
            TLRPC.Message message=res.messages.get(a);
            if (message.date > offset_date) {
              mid=message.id;
              break;
            }
          }
        }
        processLoadedMessages(res,dialog_id,count,mid,offset_date,false,classGuid,first_unread,last_message_id,unread_count,last_date,load_type,isChannel,false,loadIndex,queryFromServer,mentionsCount);
      }
    }
);
    ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
  }
}

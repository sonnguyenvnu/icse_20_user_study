public void saveDraft(final long did,TLRPC.DraftMessage draft,TLRPC.Message replyToMessage,boolean fromServer){
  SharedPreferences.Editor editor=preferences.edit();
  if (draft == null || draft instanceof TLRPC.TL_draftMessageEmpty) {
    drafts.remove(did);
    draftMessages.remove(did);
    preferences.edit().remove("" + did).remove("r_" + did).commit();
  }
 else {
    drafts.put(did,draft);
    try {
      SerializedData serializedData=new SerializedData(draft.getObjectSize());
      draft.serializeToStream(serializedData);
      editor.putString("" + did,Utilities.bytesToHex(serializedData.toByteArray()));
      serializedData.cleanup();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  if (replyToMessage == null) {
    draftMessages.remove(did);
    editor.remove("r_" + did);
  }
 else {
    draftMessages.put(did,replyToMessage);
    SerializedData serializedData=new SerializedData(replyToMessage.getObjectSize());
    replyToMessage.serializeToStream(serializedData);
    editor.putString("r_" + did,Utilities.bytesToHex(serializedData.toByteArray()));
    serializedData.cleanup();
  }
  editor.commit();
  if (fromServer) {
    if (draft.reply_to_msg_id != 0 && replyToMessage == null) {
      int lower_id=(int)did;
      TLRPC.User user=null;
      TLRPC.Chat chat=null;
      if (lower_id > 0) {
        user=MessagesController.getInstance(currentAccount).getUser(lower_id);
      }
 else {
        chat=MessagesController.getInstance(currentAccount).getChat(-lower_id);
      }
      if (user != null || chat != null) {
        long messageId=draft.reply_to_msg_id;
        final int channelIdFinal;
        if (ChatObject.isChannel(chat)) {
          messageId|=((long)chat.id) << 32;
          channelIdFinal=chat.id;
        }
 else {
          channelIdFinal=0;
        }
        final long messageIdFinal=messageId;
        MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
          try {
            TLRPC.Message message=null;
            SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT data FROM messages WHERE mid = %d",messageIdFinal));
            if (cursor.next()) {
              NativeByteBuffer data=cursor.byteBufferValue(0);
              if (data != null) {
                message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
                message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
                data.reuse();
              }
            }
            cursor.dispose();
            if (message == null) {
              if (channelIdFinal != 0) {
                final TLRPC.TL_channels_getMessages req=new TLRPC.TL_channels_getMessages();
                req.channel=MessagesController.getInstance(currentAccount).getInputChannel(channelIdFinal);
                req.id.add((int)messageIdFinal);
                ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
                  if (error == null) {
                    TLRPC.messages_Messages messagesRes=(TLRPC.messages_Messages)response;
                    if (!messagesRes.messages.isEmpty()) {
                      saveDraftReplyMessage(did,messagesRes.messages.get(0));
                    }
                  }
                }
);
              }
 else {
                TLRPC.TL_messages_getMessages req=new TLRPC.TL_messages_getMessages();
                req.id.add((int)messageIdFinal);
                ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
                  if (error == null) {
                    TLRPC.messages_Messages messagesRes=(TLRPC.messages_Messages)response;
                    if (!messagesRes.messages.isEmpty()) {
                      saveDraftReplyMessage(did,messagesRes.messages.get(0));
                    }
                  }
                }
);
              }
            }
 else {
              saveDraftReplyMessage(did,message);
            }
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
);
      }
    }
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.newDraftReceived,did);
  }
}

public void loadReplyMessagesForMessages(final ArrayList<MessageObject> messages,final long dialogId){
  if ((int)dialogId == 0) {
    final ArrayList<Long> replyMessages=new ArrayList<>();
    final LongSparseArray<ArrayList<MessageObject>> replyMessageRandomOwners=new LongSparseArray<>();
    for (int a=0; a < messages.size(); a++) {
      MessageObject messageObject=messages.get(a);
      if (messageObject.isReply() && messageObject.replyMessageObject == null) {
        long id=messageObject.messageOwner.reply_to_random_id;
        ArrayList<MessageObject> messageObjects=replyMessageRandomOwners.get(id);
        if (messageObjects == null) {
          messageObjects=new ArrayList<>();
          replyMessageRandomOwners.put(id,messageObjects);
        }
        messageObjects.add(messageObject);
        if (!replyMessages.contains(id)) {
          replyMessages.add(id);
        }
      }
    }
    if (replyMessages.isEmpty()) {
      return;
    }
    MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
      try {
        SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT m.data, m.mid, m.date, r.random_id FROM randoms as r INNER JOIN messages as m ON r.mid = m.mid WHERE r.random_id IN(%s)",TextUtils.join(",",replyMessages)));
        while (cursor.next()) {
          NativeByteBuffer data=cursor.byteBufferValue(0);
          if (data != null) {
            TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
            message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
            data.reuse();
            message.id=cursor.intValue(1);
            message.date=cursor.intValue(2);
            message.dialog_id=dialogId;
            long value=cursor.longValue(3);
            ArrayList<MessageObject> arrayList=replyMessageRandomOwners.get(value);
            replyMessageRandomOwners.remove(value);
            if (arrayList != null) {
              MessageObject messageObject=new MessageObject(currentAccount,message,false);
              for (int b=0; b < arrayList.size(); b++) {
                MessageObject object=arrayList.get(b);
                object.replyMessageObject=messageObject;
                object.messageOwner.reply_to_msg_id=messageObject.getId();
                if (object.isMegagroup()) {
                  object.replyMessageObject.messageOwner.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
                }
              }
            }
          }
        }
        cursor.dispose();
        if (replyMessageRandomOwners.size() != 0) {
          for (int b=0; b < replyMessageRandomOwners.size(); b++) {
            ArrayList<MessageObject> arrayList=replyMessageRandomOwners.valueAt(b);
            for (int a=0; a < arrayList.size(); a++) {
              arrayList.get(a).messageOwner.reply_to_random_id=0;
            }
          }
        }
        AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.replyMessagesDidLoad,dialogId));
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
);
  }
 else {
    final ArrayList<Integer> replyMessages=new ArrayList<>();
    final SparseArray<ArrayList<MessageObject>> replyMessageOwners=new SparseArray<>();
    final StringBuilder stringBuilder=new StringBuilder();
    int channelId=0;
    for (int a=0; a < messages.size(); a++) {
      MessageObject messageObject=messages.get(a);
      if (messageObject.getId() > 0 && messageObject.isReply() && messageObject.replyMessageObject == null) {
        int id=messageObject.messageOwner.reply_to_msg_id;
        long messageId=id;
        if (messageObject.messageOwner.to_id.channel_id != 0) {
          messageId|=((long)messageObject.messageOwner.to_id.channel_id) << 32;
          channelId=messageObject.messageOwner.to_id.channel_id;
        }
        if (stringBuilder.length() > 0) {
          stringBuilder.append(',');
        }
        stringBuilder.append(messageId);
        ArrayList<MessageObject> messageObjects=replyMessageOwners.get(id);
        if (messageObjects == null) {
          messageObjects=new ArrayList<>();
          replyMessageOwners.put(id,messageObjects);
        }
        messageObjects.add(messageObject);
        if (!replyMessages.contains(id)) {
          replyMessages.add(id);
        }
      }
    }
    if (replyMessages.isEmpty()) {
      return;
    }
    final int channelIdFinal=channelId;
    MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
      try {
        final ArrayList<TLRPC.Message> result=new ArrayList<>();
        final ArrayList<TLRPC.User> users=new ArrayList<>();
        final ArrayList<TLRPC.Chat> chats=new ArrayList<>();
        ArrayList<Integer> usersToLoad=new ArrayList<>();
        ArrayList<Integer> chatsToLoad=new ArrayList<>();
        SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT data, mid, date FROM messages WHERE mid IN(%s)",stringBuilder.toString()));
        while (cursor.next()) {
          NativeByteBuffer data=cursor.byteBufferValue(0);
          if (data != null) {
            TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
            message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
            data.reuse();
            message.id=cursor.intValue(1);
            message.date=cursor.intValue(2);
            message.dialog_id=dialogId;
            MessagesStorage.addUsersAndChatsFromMessage(message,usersToLoad,chatsToLoad);
            result.add(message);
            replyMessages.remove((Integer)message.id);
          }
        }
        cursor.dispose();
        if (!usersToLoad.isEmpty()) {
          MessagesStorage.getInstance(currentAccount).getUsersInternal(TextUtils.join(",",usersToLoad),users);
        }
        if (!chatsToLoad.isEmpty()) {
          MessagesStorage.getInstance(currentAccount).getChatsInternal(TextUtils.join(",",chatsToLoad),chats);
        }
        broadcastReplyMessages(result,replyMessageOwners,users,chats,dialogId,true);
        if (!replyMessages.isEmpty()) {
          if (channelIdFinal != 0) {
            final TLRPC.TL_channels_getMessages req=new TLRPC.TL_channels_getMessages();
            req.channel=MessagesController.getInstance(currentAccount).getInputChannel(channelIdFinal);
            req.id=replyMessages;
            ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
              if (error == null) {
                TLRPC.messages_Messages messagesRes=(TLRPC.messages_Messages)response;
                removeEmptyMessages(messagesRes.messages);
                ImageLoader.saveMessagesThumbs(messagesRes.messages);
                broadcastReplyMessages(messagesRes.messages,replyMessageOwners,messagesRes.users,messagesRes.chats,dialogId,false);
                MessagesStorage.getInstance(currentAccount).putUsersAndChats(messagesRes.users,messagesRes.chats,true,true);
                saveReplyMessages(replyMessageOwners,messagesRes.messages);
              }
            }
);
          }
 else {
            TLRPC.TL_messages_getMessages req=new TLRPC.TL_messages_getMessages();
            req.id=replyMessages;
            ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
              if (error == null) {
                TLRPC.messages_Messages messagesRes=(TLRPC.messages_Messages)response;
                removeEmptyMessages(messagesRes.messages);
                ImageLoader.saveMessagesThumbs(messagesRes.messages);
                broadcastReplyMessages(messagesRes.messages,replyMessageOwners,messagesRes.users,messagesRes.chats,dialogId,false);
                MessagesStorage.getInstance(currentAccount).putUsersAndChats(messagesRes.users,messagesRes.chats,true,true);
                saveReplyMessages(replyMessageOwners,messagesRes.messages);
              }
            }
);
          }
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
);
  }
}

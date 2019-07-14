public void replaceMessageIfExists(final TLRPC.Message message,int currentAccount,ArrayList<TLRPC.User> users,ArrayList<TLRPC.Chat> chats,boolean broadcast){
  if (message == null) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      long messageId=message.id;
      int channelId=0;
      if (channelId == 0) {
        channelId=message.to_id.channel_id;
      }
      if (message.to_id.channel_id != 0) {
        messageId|=((long)channelId) << 32;
      }
      SQLiteCursor cursor=null;
      try {
        cursor=database.queryFinalized(String.format(Locale.US,"SELECT uid FROM messages WHERE mid = %d LIMIT 1",messageId));
        if (!cursor.next()) {
          return;
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
 finally {
        if (cursor != null) {
          cursor.dispose();
        }
      }
      database.beginTransaction();
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO messages VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, ?, ?)");
      SQLitePreparedStatement state2=database.executeFast("REPLACE INTO media_v2 VALUES(?, ?, ?, ?, ?)");
      if (message.dialog_id == 0) {
        MessageObject.getDialogId(message);
      }
      fixUnsupportedMedia(message);
      state.requery();
      NativeByteBuffer data=new NativeByteBuffer(message.getObjectSize());
      message.serializeToStream(data);
      state.bindLong(1,messageId);
      state.bindLong(2,message.dialog_id);
      state.bindInteger(3,MessageObject.getUnreadFlags(message));
      state.bindInteger(4,message.send_state);
      state.bindInteger(5,message.date);
      state.bindByteBuffer(6,data);
      state.bindInteger(7,(MessageObject.isOut(message) ? 1 : 0));
      state.bindInteger(8,message.ttl);
      if ((message.flags & TLRPC.MESSAGE_FLAG_HAS_VIEWS) != 0) {
        state.bindInteger(9,message.views);
      }
 else {
        state.bindInteger(9,getMessageMediaType(message));
      }
      state.bindInteger(10,0);
      state.bindInteger(11,message.mentioned ? 1 : 0);
      state.step();
      if (DataQuery.canAddMessageToMedia(message)) {
        state2.requery();
        state2.bindLong(1,messageId);
        state2.bindLong(2,message.dialog_id);
        state2.bindInteger(3,message.date);
        state2.bindInteger(4,DataQuery.getMediaType(message));
        state2.bindByteBuffer(5,data);
        state2.step();
      }
      data.reuse();
      state.dispose();
      state2.dispose();
      database.commitTransaction();
      if (broadcast) {
        HashMap<Integer,TLRPC.User> userHashMap=new HashMap<>();
        HashMap<Integer,TLRPC.Chat> chatHashMap=new HashMap<>();
        for (int a=0; a < users.size(); a++) {
          TLRPC.User user=users.get(a);
          userHashMap.put(user.id,user);
        }
        for (int a=0; a < chats.size(); a++) {
          TLRPC.Chat chat=chats.get(a);
          chatHashMap.put(chat.id,chat);
        }
        MessageObject messageObject=new MessageObject(currentAccount,message,userHashMap,chatHashMap,true);
        ArrayList<MessageObject> arrayList=new ArrayList<>();
        arrayList.add(messageObject);
        AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects,messageObject.getDialogId(),arrayList));
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}

private void putMediaDatabase(final long uid,final int type,final ArrayList<TLRPC.Message> messages,final int max_id,final boolean topReached){
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      if (messages.isEmpty() || topReached) {
        MessagesStorage.getInstance(currentAccount).doneHolesInMedia(uid,max_id,type);
        if (messages.isEmpty()) {
          return;
        }
      }
      MessagesStorage.getInstance(currentAccount).getDatabase().beginTransaction();
      SQLitePreparedStatement state2=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("REPLACE INTO media_v2 VALUES(?, ?, ?, ?, ?)");
      for (      TLRPC.Message message : messages) {
        if (canAddMessageToMedia(message)) {
          long messageId=message.id;
          if (message.to_id.channel_id != 0) {
            messageId|=((long)message.to_id.channel_id) << 32;
          }
          state2.requery();
          NativeByteBuffer data=new NativeByteBuffer(message.getObjectSize());
          message.serializeToStream(data);
          state2.bindLong(1,messageId);
          state2.bindLong(2,uid);
          state2.bindInteger(3,message.date);
          state2.bindInteger(4,type);
          state2.bindByteBuffer(5,data);
          state2.step();
          data.reuse();
        }
      }
      state2.dispose();
      if (!topReached || max_id != 0) {
        int minId=topReached ? 1 : messages.get(messages.size() - 1).id;
        if (max_id != 0) {
          MessagesStorage.getInstance(currentAccount).closeHolesInMedia(uid,minId,max_id,type);
        }
 else {
          MessagesStorage.getInstance(currentAccount).closeHolesInMedia(uid,minId,Integer.MAX_VALUE,type);
        }
      }
      MessagesStorage.getInstance(currentAccount).getDatabase().commitTransaction();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}

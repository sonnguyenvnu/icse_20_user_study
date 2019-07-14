private void saveReplyMessages(final SparseArray<ArrayList<MessageObject>> replyMessageOwners,final ArrayList<TLRPC.Message> result){
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      MessagesStorage.getInstance(currentAccount).getDatabase().beginTransaction();
      SQLitePreparedStatement state=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("UPDATE messages SET replydata = ? WHERE mid = ?");
      for (int a=0; a < result.size(); a++) {
        TLRPC.Message message=result.get(a);
        ArrayList<MessageObject> messageObjects=replyMessageOwners.get(message.id);
        if (messageObjects != null) {
          NativeByteBuffer data=new NativeByteBuffer(message.getObjectSize());
          message.serializeToStream(data);
          for (int b=0; b < messageObjects.size(); b++) {
            MessageObject messageObject=messageObjects.get(b);
            state.requery();
            long messageId=messageObject.getId();
            if (messageObject.messageOwner.to_id.channel_id != 0) {
              messageId|=((long)messageObject.messageOwner.to_id.channel_id) << 32;
            }
            state.bindByteBuffer(1,data);
            state.bindLong(2,messageId);
            state.step();
          }
          data.reuse();
        }
      }
      state.dispose();
      MessagesStorage.getInstance(currentAccount).getDatabase().commitTransaction();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}

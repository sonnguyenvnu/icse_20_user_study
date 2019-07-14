private void resendMessages(final int startSeq,final int endSeq,final TLRPC.EncryptedChat encryptedChat){
  if (encryptedChat == null || endSeq - startSeq < 0) {
    return;
  }
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      int sSeq=startSeq;
      if (encryptedChat.admin_id == UserConfig.getInstance(currentAccount).getClientUserId() && sSeq % 2 == 0) {
        sSeq++;
      }
      SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT uid FROM requested_holes WHERE uid = %d AND ((seq_out_start >= %d AND %d <= seq_out_end) OR (seq_out_start >= %d AND %d <= seq_out_end))",encryptedChat.id,sSeq,sSeq,endSeq,endSeq));
      boolean exists=cursor.next();
      cursor.dispose();
      if (exists) {
        return;
      }
      long dialog_id=((long)encryptedChat.id) << 32;
      SparseArray<TLRPC.Message> messagesToResend=new SparseArray<>();
      final ArrayList<TLRPC.Message> messages=new ArrayList<>();
      for (int a=sSeq; a < endSeq; a+=2) {
        messagesToResend.put(a,null);
      }
      cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT m.data, r.random_id, s.seq_in, s.seq_out, m.ttl, s.mid FROM messages_seq as s LEFT JOIN randoms as r ON r.mid = s.mid LEFT JOIN messages as m ON m.mid = s.mid WHERE m.uid = %d AND m.out = 1 AND s.seq_out >= %d AND s.seq_out <= %d ORDER BY seq_out ASC",dialog_id,sSeq,endSeq));
      while (cursor.next()) {
        TLRPC.Message message;
        long random_id=cursor.longValue(1);
        if (random_id == 0) {
          random_id=Utilities.random.nextLong();
        }
        int seq_in=cursor.intValue(2);
        int seq_out=cursor.intValue(3);
        int mid=cursor.intValue(5);
        NativeByteBuffer data=cursor.byteBufferValue(0);
        if (data != null) {
          message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
          message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
          data.reuse();
          message.random_id=random_id;
          message.dialog_id=dialog_id;
          message.seq_in=seq_in;
          message.seq_out=seq_out;
          message.ttl=cursor.intValue(4);
        }
 else {
          message=createDeleteMessage(mid,seq_out,seq_in,random_id,encryptedChat);
        }
        messages.add(message);
        messagesToResend.remove(seq_out);
      }
      cursor.dispose();
      if (messagesToResend.size() != 0) {
        for (int a=0; a < messagesToResend.size(); a++) {
          messages.add(createDeleteMessage(UserConfig.getInstance(currentAccount).getNewMessageId(),messagesToResend.keyAt(a),0,Utilities.random.nextLong(),encryptedChat));
        }
        UserConfig.getInstance(currentAccount).saveConfig(false);
      }
      Collections.sort(messages,(lhs,rhs) -> AndroidUtilities.compare(lhs.seq_out,rhs.seq_out));
      ArrayList<TLRPC.EncryptedChat> encryptedChats=new ArrayList<>();
      encryptedChats.add(encryptedChat);
      AndroidUtilities.runOnUIThread(() -> {
        for (int a=0; a < messages.size(); a++) {
          TLRPC.Message message=messages.get(a);
          MessageObject messageObject=new MessageObject(currentAccount,message,false);
          messageObject.resendAsIs=true;
          SendMessagesHelper.getInstance(currentAccount).retrySendMessage(messageObject,true);
        }
      }
);
      SendMessagesHelper.getInstance(currentAccount).processUnsentMessages(messages,new ArrayList<>(),new ArrayList<>(),encryptedChats);
      MessagesStorage.getInstance(currentAccount).getDatabase().executeFast(String.format(Locale.US,"REPLACE INTO requested_holes VALUES(%d, %d, %d)",encryptedChat.id,sSeq,endSeq)).stepThis().dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}

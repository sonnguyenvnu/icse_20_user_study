public void markMessageAsSendError(final TLRPC.Message message){
  storageQueue.postRunnable(() -> {
    try {
      long messageId=message.id;
      if (message.to_id.channel_id != 0) {
        messageId|=((long)message.to_id.channel_id) << 32;
      }
      database.executeFast("UPDATE messages SET send_state = 2 WHERE mid = " + messageId).stepThis().dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}

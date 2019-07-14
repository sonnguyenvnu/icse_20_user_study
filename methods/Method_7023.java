public void putMessages(final ArrayList<TLRPC.Message> messages,final boolean withTransaction,boolean useQueue,final boolean doNotUpdateDialogDate,final int downloadMask,final boolean ifNoLastMessage){
  if (messages.size() == 0) {
    return;
  }
  if (useQueue) {
    storageQueue.postRunnable(() -> putMessagesInternal(messages,withTransaction,doNotUpdateDialogDate,downloadMask,ifNoLastMessage));
  }
 else {
    putMessagesInternal(messages,withTransaction,doNotUpdateDialogDate,downloadMask,ifNoLastMessage);
  }
}

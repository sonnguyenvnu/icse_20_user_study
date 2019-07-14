public void putDialogs(final TLRPC.messages_Dialogs dialogs,final int check){
  if (dialogs.dialogs.isEmpty()) {
    return;
  }
  storageQueue.postRunnable(() -> {
    putDialogsInternal(dialogs,check);
    try {
      loadUnreadMessages();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}

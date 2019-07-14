public void readAllDialogs(){
  storageQueue.postRunnable(() -> {
    try {
      ArrayList<Integer> usersToLoad=new ArrayList<>();
      ArrayList<Integer> chatsToLoad=new ArrayList<>();
      ArrayList<Integer> encryptedChatIds=new ArrayList<>();
      final LongSparseArray<ReadDialog> dialogs=new LongSparseArray<>();
      SQLiteCursor cursor=database.queryFinalized("SELECT did, last_mid, unread_count, date FROM dialogs WHERE unread_count != 0");
      while (cursor.next()) {
        long did=cursor.longValue(0);
        if (DialogObject.isFolderDialogId(did)) {
          continue;
        }
        ReadDialog dialog=new ReadDialog();
        dialog.lastMid=cursor.intValue(1);
        dialog.unreadCount=cursor.intValue(2);
        dialog.date=cursor.intValue(3);
        dialogs.put(did,dialog);
        int lower_id=(int)did;
        int high_id=(int)(did >> 32);
        if (lower_id != 0) {
          if (lower_id < 0) {
            if (!chatsToLoad.contains(-lower_id)) {
              chatsToLoad.add(-lower_id);
            }
          }
 else {
            if (!usersToLoad.contains(lower_id)) {
              usersToLoad.add(lower_id);
            }
          }
        }
 else {
          if (!encryptedChatIds.contains(high_id)) {
            encryptedChatIds.add(high_id);
          }
        }
      }
      cursor.dispose();
      final ArrayList<TLRPC.User> users=new ArrayList<>();
      final ArrayList<TLRPC.Chat> chats=new ArrayList<>();
      final ArrayList<TLRPC.EncryptedChat> encryptedChats=new ArrayList<>();
      if (!encryptedChatIds.isEmpty()) {
        getEncryptedChatsInternal(TextUtils.join(",",encryptedChatIds),encryptedChats,usersToLoad);
      }
      if (!usersToLoad.isEmpty()) {
        getUsersInternal(TextUtils.join(",",usersToLoad),users);
      }
      if (!chatsToLoad.isEmpty()) {
        getChatsInternal(TextUtils.join(",",chatsToLoad),chats);
      }
      AndroidUtilities.runOnUIThread(() -> {
        MessagesController.getInstance(currentAccount).putUsers(users,true);
        MessagesController.getInstance(currentAccount).putChats(chats,true);
        MessagesController.getInstance(currentAccount).putEncryptedChats(encryptedChats,true);
        for (int a=0; a < dialogs.size(); a++) {
          long did=dialogs.keyAt(a);
          ReadDialog dialog=dialogs.valueAt(a);
          MessagesController.getInstance(currentAccount).markDialogAsRead(did,dialog.lastMid,dialog.lastMid,dialog.date,false,dialog.unreadCount,true);
        }
      }
);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}

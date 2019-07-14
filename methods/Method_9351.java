private void loadExceptions(){
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    ArrayList<NotificationsSettingsActivity.NotificationException> usersResult=new ArrayList<>();
    ArrayList<NotificationsSettingsActivity.NotificationException> chatsResult=new ArrayList<>();
    ArrayList<NotificationsSettingsActivity.NotificationException> channelsResult=new ArrayList<>();
    LongSparseArray<NotificationsSettingsActivity.NotificationException> waitingForLoadExceptions=new LongSparseArray<>();
    ArrayList<Integer> usersToLoad=new ArrayList<>();
    ArrayList<Integer> chatsToLoad=new ArrayList<>();
    ArrayList<Integer> encryptedChatsToLoad=new ArrayList<>();
    ArrayList<TLRPC.User> users=new ArrayList<>();
    ArrayList<TLRPC.Chat> chats=new ArrayList<>();
    ArrayList<TLRPC.EncryptedChat> encryptedChats=new ArrayList<>();
    int selfId=UserConfig.getInstance(currentAccount).clientUserId;
    SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
    Map<String,?> values=preferences.getAll();
    for (    Map.Entry<String,?> entry : values.entrySet()) {
      String key=entry.getKey();
      if (key.startsWith("notify2_")) {
        key=key.replace("notify2_","");
        long did=Utilities.parseLong(key);
        if (did != 0 && did != selfId) {
          NotificationsSettingsActivity.NotificationException exception=new NotificationsSettingsActivity.NotificationException();
          exception.did=did;
          exception.hasCustom=preferences.getBoolean("custom_" + did,false);
          exception.notify=(Integer)entry.getValue();
          if (exception.notify != 0) {
            Integer time=(Integer)values.get("notifyuntil_" + key);
            if (time != null) {
              exception.muteUntil=time;
            }
          }
          int lower_id=(int)did;
          int high_id=(int)(did << 32);
          if (lower_id != 0) {
            if (lower_id > 0) {
              TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(lower_id);
              if (user == null) {
                usersToLoad.add(lower_id);
                waitingForLoadExceptions.put(did,exception);
              }
 else               if (user.deleted) {
                continue;
              }
              usersResult.add(exception);
            }
 else {
              TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-lower_id);
              if (chat == null) {
                chatsToLoad.add(-lower_id);
                waitingForLoadExceptions.put(did,exception);
                continue;
              }
 else               if (chat.left || chat.kicked || chat.migrated_to != null) {
                continue;
              }
              if (ChatObject.isChannel(chat) && !chat.megagroup) {
                channelsResult.add(exception);
              }
 else {
                chatsResult.add(exception);
              }
            }
          }
 else           if (high_id != 0) {
            TLRPC.EncryptedChat encryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat(high_id);
            if (encryptedChat == null) {
              encryptedChatsToLoad.add(high_id);
              waitingForLoadExceptions.put(did,exception);
            }
 else {
              TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(encryptedChat.user_id);
              if (user == null) {
                usersToLoad.add(encryptedChat.user_id);
                waitingForLoadExceptions.put(encryptedChat.user_id,exception);
              }
 else               if (user.deleted) {
                continue;
              }
            }
            usersResult.add(exception);
          }
        }
      }
    }
    if (waitingForLoadExceptions.size() != 0) {
      try {
        if (!encryptedChatsToLoad.isEmpty()) {
          MessagesStorage.getInstance(currentAccount).getEncryptedChatsInternal(TextUtils.join(",",encryptedChatsToLoad),encryptedChats,usersToLoad);
        }
        if (!usersToLoad.isEmpty()) {
          MessagesStorage.getInstance(currentAccount).getUsersInternal(TextUtils.join(",",usersToLoad),users);
        }
        if (!chatsToLoad.isEmpty()) {
          MessagesStorage.getInstance(currentAccount).getChatsInternal(TextUtils.join(",",chatsToLoad),chats);
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      for (int a=0, size=chats.size(); a < size; a++) {
        TLRPC.Chat chat=chats.get(a);
        if (chat.left || chat.kicked || chat.migrated_to != null) {
          continue;
        }
        NotificationsSettingsActivity.NotificationException exception=waitingForLoadExceptions.get(-chat.id);
        waitingForLoadExceptions.remove(-chat.id);
        if (exception != null) {
          if (ChatObject.isChannel(chat) && !chat.megagroup) {
            channelsResult.add(exception);
          }
 else {
            chatsResult.add(exception);
          }
        }
      }
      for (int a=0, size=users.size(); a < size; a++) {
        TLRPC.User user=users.get(a);
        if (user.deleted) {
          continue;
        }
        waitingForLoadExceptions.remove(user.id);
      }
      for (int a=0, size=encryptedChats.size(); a < size; a++) {
        TLRPC.EncryptedChat encryptedChat=encryptedChats.get(a);
        waitingForLoadExceptions.remove(((long)encryptedChat.id) << 32);
      }
      for (int a=0, size=waitingForLoadExceptions.size(); a < size; a++) {
        long did=waitingForLoadExceptions.keyAt(a);
        if ((int)did < 0) {
          chatsResult.remove(waitingForLoadExceptions.valueAt(a));
          channelsResult.remove(waitingForLoadExceptions.valueAt(a));
        }
 else {
          usersResult.remove(waitingForLoadExceptions.valueAt(a));
        }
      }
    }
    AndroidUtilities.runOnUIThread(() -> {
      MessagesController.getInstance(currentAccount).putUsers(users,true);
      MessagesController.getInstance(currentAccount).putChats(chats,true);
      MessagesController.getInstance(currentAccount).putEncryptedChats(encryptedChats,true);
      if (currentType == NotificationsController.TYPE_PRIVATE) {
        exceptions=usersResult;
      }
 else       if (currentType == NotificationsController.TYPE_GROUP) {
        exceptions=chatsResult;
      }
 else {
        exceptions=channelsResult;
      }
      updateRows();
      adapter.notifyDataSetChanged();
    }
);
  }
);
}

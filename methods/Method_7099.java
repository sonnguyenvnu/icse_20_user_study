public void processLoadedUnreadMessages(final LongSparseArray<Integer> dialogs,final ArrayList<TLRPC.Message> messages,ArrayList<MessageObject> push,final ArrayList<TLRPC.User> users,final ArrayList<TLRPC.Chat> chats,final ArrayList<TLRPC.EncryptedChat> encryptedChats){
  MessagesController.getInstance(currentAccount).putUsers(users,true);
  MessagesController.getInstance(currentAccount).putChats(chats,true);
  MessagesController.getInstance(currentAccount).putEncryptedChats(encryptedChats,true);
  notificationsQueue.postRunnable(() -> {
    pushDialogs.clear();
    pushMessages.clear();
    pushMessagesDict.clear();
    total_unread_count=0;
    personal_count=0;
    SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
    LongSparseArray<Boolean> settingsCache=new LongSparseArray<>();
    if (messages != null) {
      for (int a=0; a < messages.size(); a++) {
        TLRPC.Message message=messages.get(a);
        if (message != null && message.silent && (message.action instanceof TLRPC.TL_messageActionContactSignUp || message.action instanceof TLRPC.TL_messageActionUserJoined)) {
          continue;
        }
        long mid=message.id;
        if (message.to_id.channel_id != 0) {
          mid|=((long)message.to_id.channel_id) << 32;
        }
        if (pushMessagesDict.indexOfKey(mid) >= 0) {
          continue;
        }
        MessageObject messageObject=new MessageObject(currentAccount,message,false);
        if (isPersonalMessage(messageObject)) {
          personal_count++;
        }
        long dialog_id=messageObject.getDialogId();
        long original_dialog_id=dialog_id;
        if (messageObject.messageOwner.mentioned) {
          dialog_id=messageObject.messageOwner.from_id;
        }
        int index=settingsCache.indexOfKey(dialog_id);
        boolean value;
        if (index >= 0) {
          value=settingsCache.valueAt(index);
        }
 else {
          int notifyOverride=getNotifyOverride(preferences,dialog_id);
          if (notifyOverride == -1) {
            value=isGlobalNotificationsEnabled(dialog_id);
          }
 else {
            value=notifyOverride != 2;
          }
          settingsCache.put(dialog_id,value);
        }
        if (!value || dialog_id == opened_dialog_id && ApplicationLoader.isScreenOn) {
          continue;
        }
        pushMessagesDict.put(mid,messageObject);
        pushMessages.add(0,messageObject);
        if (original_dialog_id != dialog_id) {
          Integer current=pushDialogsOverrideMention.get(original_dialog_id);
          pushDialogsOverrideMention.put(original_dialog_id,current == null ? 1 : current + 1);
        }
      }
    }
    for (int a=0; a < dialogs.size(); a++) {
      long dialog_id=dialogs.keyAt(a);
      int index=settingsCache.indexOfKey(dialog_id);
      boolean value;
      if (index >= 0) {
        value=settingsCache.valueAt(index);
      }
 else {
        int notifyOverride=getNotifyOverride(preferences,dialog_id);
        if (notifyOverride == -1) {
          value=isGlobalNotificationsEnabled(dialog_id);
        }
 else {
          value=notifyOverride != 2;
        }
        settingsCache.put(dialog_id,value);
      }
      if (!value) {
        continue;
      }
      int count=dialogs.valueAt(a);
      pushDialogs.put(dialog_id,count);
      total_unread_count+=count;
    }
    if (push != null) {
      for (int a=0; a < push.size(); a++) {
        MessageObject messageObject=push.get(a);
        long mid=messageObject.getId();
        if (messageObject.messageOwner.to_id.channel_id != 0) {
          mid|=((long)messageObject.messageOwner.to_id.channel_id) << 32;
        }
        if (pushMessagesDict.indexOfKey(mid) >= 0) {
          continue;
        }
        if (isPersonalMessage(messageObject)) {
          personal_count++;
        }
        long dialog_id=messageObject.getDialogId();
        long original_dialog_id=dialog_id;
        long random_id=messageObject.messageOwner.random_id;
        if (messageObject.messageOwner.mentioned) {
          dialog_id=messageObject.messageOwner.from_id;
        }
        int index=settingsCache.indexOfKey(dialog_id);
        boolean value;
        if (index >= 0) {
          value=settingsCache.valueAt(index);
        }
 else {
          int notifyOverride=getNotifyOverride(preferences,dialog_id);
          if (notifyOverride == -1) {
            value=isGlobalNotificationsEnabled(dialog_id);
          }
 else {
            value=notifyOverride != 2;
          }
          settingsCache.put(dialog_id,value);
        }
        if (!value || dialog_id == opened_dialog_id && ApplicationLoader.isScreenOn) {
          continue;
        }
        if (mid != 0) {
          pushMessagesDict.put(mid,messageObject);
        }
 else         if (random_id != 0) {
          fcmRandomMessagesDict.put(random_id,messageObject);
        }
        pushMessages.add(0,messageObject);
        if (original_dialog_id != dialog_id) {
          Integer current=pushDialogsOverrideMention.get(original_dialog_id);
          pushDialogsOverrideMention.put(original_dialog_id,current == null ? 1 : current + 1);
        }
        Integer currentCount=pushDialogs.get(dialog_id);
        Integer newCount=currentCount != null ? currentCount + 1 : 1;
        if (currentCount != null) {
          total_unread_count-=currentCount;
        }
        total_unread_count+=newCount;
        pushDialogs.put(dialog_id,newCount);
      }
    }
    final int pushDialogsCount=pushDialogs.size();
    AndroidUtilities.runOnUIThread(() -> {
      if (total_unread_count == 0) {
        popupMessages.clear();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pushMessagesUpdated);
      }
      NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.notificationsCountUpdated,currentAccount);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsUnreadCounterChanged,pushDialogsCount);
    }
);
    showOrUpdateNotification(SystemClock.elapsedRealtime() / 1000 < 60);
    if (showBadgeNumber) {
      setBadge(getTotalAllUnreadCount());
    }
  }
);
}

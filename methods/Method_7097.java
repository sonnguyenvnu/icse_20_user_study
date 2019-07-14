public void processNewMessages(final ArrayList<MessageObject> messageObjects,final boolean isLast,final boolean isFcm,CountDownLatch countDownLatch){
  if (messageObjects.isEmpty()) {
    if (countDownLatch != null) {
      countDownLatch.countDown();
    }
    return;
  }
  final ArrayList<MessageObject> popupArrayAdd=new ArrayList<>(0);
  notificationsQueue.postRunnable(() -> {
    boolean added=false;
    boolean edited=false;
    LongSparseArray<Boolean> settingsCache=new LongSparseArray<>();
    SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
    boolean allowPinned=preferences.getBoolean("PinnedMessages",true);
    int popup=0;
    for (int a=0; a < messageObjects.size(); a++) {
      MessageObject messageObject=messageObjects.get(a);
      if (messageObject.messageOwner != null && messageObject.messageOwner.silent && (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionContactSignUp || messageObject.messageOwner.action instanceof TLRPC.TL_messageActionUserJoined)) {
        continue;
      }
      long mid=messageObject.getId();
      long random_id=messageObject.isFcmMessage() ? messageObject.messageOwner.random_id : 0;
      long dialog_id=messageObject.getDialogId();
      int lower_id=(int)dialog_id;
      boolean isChannel;
      if (messageObject.messageOwner.to_id.channel_id != 0) {
        mid|=((long)messageObject.messageOwner.to_id.channel_id) << 32;
        isChannel=true;
      }
 else {
        isChannel=false;
      }
      MessageObject oldMessageObject=pushMessagesDict.get(mid);
      if (oldMessageObject == null && messageObject.messageOwner.random_id != 0) {
        oldMessageObject=fcmRandomMessagesDict.get(messageObject.messageOwner.random_id);
        if (oldMessageObject != null) {
          fcmRandomMessagesDict.remove(messageObject.messageOwner.random_id);
        }
      }
      if (oldMessageObject != null) {
        if (oldMessageObject.isFcmMessage()) {
          pushMessagesDict.put(mid,messageObject);
          int idxOld=pushMessages.indexOf(oldMessageObject);
          if (idxOld >= 0) {
            pushMessages.set(idxOld,messageObject);
            popup=addToPopupMessages(popupArrayAdd,messageObject,lower_id,dialog_id,isChannel,preferences);
          }
          if (isFcm && (edited=messageObject.localEdit)) {
            MessagesStorage.getInstance(currentAccount).putPushMessage(messageObject);
          }
        }
        continue;
      }
      if (edited) {
        continue;
      }
      if (isFcm) {
        MessagesStorage.getInstance(currentAccount).putPushMessage(messageObject);
      }
      long original_dialog_id=dialog_id;
      if (dialog_id == opened_dialog_id && ApplicationLoader.isScreenOn) {
        if (!isFcm) {
          playInChatSound();
        }
        continue;
      }
      if (messageObject.messageOwner.mentioned) {
        if (!allowPinned && messageObject.messageOwner.action instanceof TLRPC.TL_messageActionPinMessage) {
          continue;
        }
        dialog_id=messageObject.messageOwner.from_id;
      }
      if (isPersonalMessage(messageObject)) {
        personal_count++;
      }
      added=true;
      boolean isChat=lower_id < 0;
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
      if (value) {
        if (!isFcm) {
          popup=addToPopupMessages(popupArrayAdd,messageObject,lower_id,dialog_id,isChannel,preferences);
        }
        delayedPushMessages.add(messageObject);
        pushMessages.add(0,messageObject);
        if (mid != 0) {
          pushMessagesDict.put(mid,messageObject);
        }
 else         if (random_id != 0) {
          fcmRandomMessagesDict.put(random_id,messageObject);
        }
        if (original_dialog_id != dialog_id) {
          Integer current=pushDialogsOverrideMention.get(original_dialog_id);
          pushDialogsOverrideMention.put(original_dialog_id,current == null ? 1 : current + 1);
        }
      }
    }
    if (added) {
      notifyCheck=isLast;
    }
    if (!popupArrayAdd.isEmpty() && !AndroidUtilities.needShowPasscode(false)) {
      final int popupFinal=popup;
      AndroidUtilities.runOnUIThread(() -> {
        popupMessages.addAll(0,popupArrayAdd);
        if (ApplicationLoader.mainInterfacePaused || !ApplicationLoader.isScreenOn && !SharedConfig.isWaitingForPasscodeEnter) {
          if (popupFinal == 3 || popupFinal == 1 && ApplicationLoader.isScreenOn || popupFinal == 2 && !ApplicationLoader.isScreenOn) {
            Intent popupIntent=new Intent(ApplicationLoader.applicationContext,PopupNotificationActivity.class);
            popupIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NO_USER_ACTION | Intent.FLAG_FROM_BACKGROUND);
            try {
              ApplicationLoader.applicationContext.startActivity(popupIntent);
            }
 catch (            Throwable ignore) {
            }
          }
        }
      }
);
    }
    if (isFcm) {
      if (edited) {
        delayedPushMessages.clear();
        showOrUpdateNotification(notifyCheck);
      }
 else       if (added) {
        long dialog_id=messageObjects.get(0).getDialogId();
        int old_unread_count=total_unread_count;
        int notifyOverride=getNotifyOverride(preferences,dialog_id);
        boolean canAddValue;
        if (notifyOverride == -1) {
          canAddValue=isGlobalNotificationsEnabled(dialog_id);
        }
 else {
          canAddValue=notifyOverride != 2;
        }
        Integer currentCount=pushDialogs.get(dialog_id);
        Integer newCount=currentCount != null ? currentCount + 1 : 1;
        if (notifyCheck && !canAddValue) {
          Integer override=pushDialogsOverrideMention.get(dialog_id);
          if (override != null && override != 0) {
            canAddValue=true;
            newCount=override;
          }
        }
        if (canAddValue) {
          if (currentCount != null) {
            total_unread_count-=currentCount;
          }
          total_unread_count+=newCount;
          pushDialogs.put(dialog_id,newCount);
        }
        if (old_unread_count != total_unread_count) {
          delayedPushMessages.clear();
          showOrUpdateNotification(notifyCheck);
          final int pushDialogsCount=pushDialogs.size();
          AndroidUtilities.runOnUIThread(() -> {
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.notificationsCountUpdated,currentAccount);
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsUnreadCounterChanged,pushDialogsCount);
          }
);
        }
        notifyCheck=false;
        if (showBadgeNumber) {
          setBadge(getTotalAllUnreadCount());
        }
      }
    }
    if (countDownLatch != null) {
      countDownLatch.countDown();
    }
  }
);
}

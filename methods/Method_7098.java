public void processDialogsUpdateRead(final LongSparseArray<Integer> dialogsToUpdate){
  final ArrayList<MessageObject> popupArrayToRemove=new ArrayList<>();
  notificationsQueue.postRunnable(() -> {
    int old_unread_count=total_unread_count;
    SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
    for (int b=0; b < dialogsToUpdate.size(); b++) {
      long dialog_id=dialogsToUpdate.keyAt(b);
      int notifyOverride=getNotifyOverride(preferences,dialog_id);
      boolean canAddValue;
      if (notifyOverride == -1) {
        canAddValue=isGlobalNotificationsEnabled(dialog_id);
      }
 else {
        canAddValue=notifyOverride != 2;
      }
      Integer currentCount=pushDialogs.get(dialog_id);
      Integer newCount=dialogsToUpdate.get(dialog_id);
      if (notifyCheck && !canAddValue) {
        Integer override=pushDialogsOverrideMention.get(dialog_id);
        if (override != null && override != 0) {
          canAddValue=true;
          newCount=override;
        }
      }
      if (newCount == 0) {
        smartNotificationsDialogs.remove(dialog_id);
      }
      if (newCount < 0) {
        if (currentCount == null) {
          continue;
        }
        newCount=currentCount + newCount;
      }
      if (canAddValue || newCount == 0) {
        if (currentCount != null) {
          total_unread_count-=currentCount;
        }
      }
      if (newCount == 0) {
        pushDialogs.remove(dialog_id);
        pushDialogsOverrideMention.remove(dialog_id);
        for (int a=0; a < pushMessages.size(); a++) {
          MessageObject messageObject=pushMessages.get(a);
          if (messageObject.getDialogId() == dialog_id) {
            if (isPersonalMessage(messageObject)) {
              personal_count--;
            }
            pushMessages.remove(a);
            a--;
            delayedPushMessages.remove(messageObject);
            long mid=messageObject.getId();
            if (messageObject.messageOwner.to_id.channel_id != 0) {
              mid|=((long)messageObject.messageOwner.to_id.channel_id) << 32;
            }
            pushMessagesDict.remove(mid);
            popupArrayToRemove.add(messageObject);
          }
        }
      }
 else       if (canAddValue) {
        total_unread_count+=newCount;
        pushDialogs.put(dialog_id,newCount);
      }
    }
    if (!popupArrayToRemove.isEmpty()) {
      AndroidUtilities.runOnUIThread(() -> {
        for (int a=0, size=popupArrayToRemove.size(); a < size; a++) {
          popupMessages.remove(popupArrayToRemove.get(a));
        }
      }
);
    }
    if (old_unread_count != total_unread_count) {
      if (!notifyCheck) {
        delayedPushMessages.clear();
        showOrUpdateNotification(notifyCheck);
      }
 else {
        scheduleNotificationDelay(lastOnlineFromOtherDevice > ConnectionsManager.getInstance(currentAccount).getCurrentTime());
      }
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
);
}

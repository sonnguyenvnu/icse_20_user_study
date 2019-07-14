public void removeDeletedHisoryFromNotifications(final SparseIntArray deletedMessages){
  final ArrayList<MessageObject> popupArrayRemove=new ArrayList<>(0);
  notificationsQueue.postRunnable(() -> {
    int old_unread_count=total_unread_count;
    SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
    for (int a=0; a < deletedMessages.size(); a++) {
      int key=deletedMessages.keyAt(a);
      long dialog_id=-key;
      int id=deletedMessages.get(key);
      Integer currentCount=pushDialogs.get(dialog_id);
      if (currentCount == null) {
        currentCount=0;
      }
      Integer newCount=currentCount;
      for (int c=0; c < pushMessages.size(); c++) {
        MessageObject messageObject=pushMessages.get(c);
        if (messageObject.getDialogId() == dialog_id && messageObject.getId() <= id) {
          pushMessagesDict.remove(messageObject.getIdWithChannel());
          delayedPushMessages.remove(messageObject);
          pushMessages.remove(messageObject);
          c--;
          if (isPersonalMessage(messageObject)) {
            personal_count--;
          }
          popupArrayRemove.add(messageObject);
          newCount--;
        }
      }
      if (newCount <= 0) {
        newCount=0;
        smartNotificationsDialogs.remove(dialog_id);
      }
      if (!newCount.equals(currentCount)) {
        total_unread_count-=currentCount;
        total_unread_count+=newCount;
        pushDialogs.put(dialog_id,newCount);
      }
      if (newCount == 0) {
        pushDialogs.remove(dialog_id);
        pushDialogsOverrideMention.remove(dialog_id);
      }
    }
    if (popupArrayRemove.isEmpty()) {
      AndroidUtilities.runOnUIThread(() -> {
        for (int a=0, size=popupArrayRemove.size(); a < size; a++) {
          popupMessages.remove(popupArrayRemove.get(a));
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

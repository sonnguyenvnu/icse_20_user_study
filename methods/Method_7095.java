public void processReadMessages(final SparseLongArray inbox,final long dialog_id,final int max_date,final int max_id,final boolean isPopup){
  final ArrayList<MessageObject> popupArrayRemove=new ArrayList<>(0);
  notificationsQueue.postRunnable(() -> {
    if (inbox != null) {
      for (int b=0; b < inbox.size(); b++) {
        int key=inbox.keyAt(b);
        long messageId=inbox.get(key);
        for (int a=0; a < pushMessages.size(); a++) {
          MessageObject messageObject=pushMessages.get(a);
          if (messageObject.getDialogId() == key && messageObject.getId() <= (int)messageId) {
            if (isPersonalMessage(messageObject)) {
              personal_count--;
            }
            popupArrayRemove.add(messageObject);
            long mid=messageObject.getId();
            if (messageObject.messageOwner.to_id.channel_id != 0) {
              mid|=((long)messageObject.messageOwner.to_id.channel_id) << 32;
            }
            pushMessagesDict.remove(mid);
            delayedPushMessages.remove(messageObject);
            pushMessages.remove(a);
            a--;
          }
        }
      }
    }
    if (dialog_id != 0 && (max_id != 0 || max_date != 0)) {
      for (int a=0; a < pushMessages.size(); a++) {
        MessageObject messageObject=pushMessages.get(a);
        if (messageObject.getDialogId() == dialog_id) {
          boolean remove=false;
          if (max_date != 0) {
            if (messageObject.messageOwner.date <= max_date) {
              remove=true;
            }
          }
 else {
            if (!isPopup) {
              if (messageObject.getId() <= max_id || max_id < 0) {
                remove=true;
              }
            }
 else {
              if (messageObject.getId() == max_id || max_id < 0) {
                remove=true;
              }
            }
          }
          if (remove) {
            if (isPersonalMessage(messageObject)) {
              personal_count--;
            }
            pushMessages.remove(a);
            delayedPushMessages.remove(messageObject);
            popupArrayRemove.add(messageObject);
            long mid=messageObject.getId();
            if (messageObject.messageOwner.to_id.channel_id != 0) {
              mid|=((long)messageObject.messageOwner.to_id.channel_id) << 32;
            }
            pushMessagesDict.remove(mid);
            a--;
          }
        }
      }
    }
    if (!popupArrayRemove.isEmpty()) {
      AndroidUtilities.runOnUIThread(() -> {
        for (int a=0, size=popupArrayRemove.size(); a < size; a++) {
          popupMessages.remove(popupArrayRemove.get(a));
        }
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pushMessagesUpdated);
      }
);
    }
  }
);
}

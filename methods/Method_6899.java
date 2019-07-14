public void markDialogAsRead(final long dialogId,final int maxPositiveId,final int maxNegativeId,final int maxDate,final boolean popup,final int countDiff,final boolean readNow){
  int lower_part=(int)dialogId;
  int high_id=(int)(dialogId >> 32);
  boolean createReadTask;
  boolean countMessages=NotificationsController.getInstance(currentAccount).showBadgeMessages;
  if (lower_part != 0) {
    if (maxPositiveId == 0 || high_id == 1) {
      return;
    }
    long maxMessageId=maxPositiveId;
    long minMessageId=maxNegativeId;
    boolean isChannel=false;
    if (lower_part < 0) {
      TLRPC.Chat chat=getChat(-lower_part);
      if (ChatObject.isChannel(chat)) {
        maxMessageId|=((long)-lower_part) << 32;
        minMessageId|=((long)-lower_part) << 32;
        isChannel=true;
      }
    }
    Integer value=dialogs_read_inbox_max.get(dialogId);
    if (value == null) {
      value=0;
    }
    dialogs_read_inbox_max.put(dialogId,Math.max(value,maxPositiveId));
    MessagesStorage.getInstance(currentAccount).processPendingRead(dialogId,maxMessageId,minMessageId,maxDate,isChannel);
    MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> AndroidUtilities.runOnUIThread(() -> {
      TLRPC.Dialog dialog=dialogs_dict.get(dialogId);
      if (dialog != null) {
        int prevCount=dialog.unread_count;
        if (countDiff == 0 || maxPositiveId >= dialog.top_message) {
          dialog.unread_count=0;
        }
 else {
          dialog.unread_count=Math.max(dialog.unread_count - countDiff,0);
          if (maxPositiveId != Integer.MIN_VALUE && dialog.unread_count > dialog.top_message - maxPositiveId) {
            dialog.unread_count=dialog.top_message - maxPositiveId;
          }
        }
        if (dialog.folder_id != 0) {
          TLRPC.Dialog folder=dialogs_dict.get(DialogObject.makeFolderDialogId(dialog.folder_id));
          if (folder != null) {
            if (countMessages) {
              if (isDialogMuted(dialog.id)) {
                folder.unread_count-=(prevCount - dialog.unread_count);
              }
 else {
                folder.unread_mentions_count-=(prevCount - dialog.unread_count);
              }
            }
 else             if (dialog.unread_count == 0) {
              if (isDialogMuted(dialog.id)) {
                folder.unread_count--;
              }
 else {
                folder.unread_mentions_count--;
              }
            }
          }
        }
        if ((prevCount != 0 || dialog.unread_mark) && dialog.unread_count == 0 && !isDialogMuted(dialogId)) {
          unreadUnmutedDialogs--;
        }
        if (dialog.unread_mark) {
          dialog.unread_mark=false;
          MessagesStorage.getInstance(currentAccount).setDialogUnread(dialog.id,false);
        }
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_READ_DIALOG_MESSAGE);
      }
      if (!popup) {
        NotificationsController.getInstance(currentAccount).processReadMessages(null,dialogId,0,maxPositiveId,false);
        LongSparseArray<Integer> dialogsToUpdate=new LongSparseArray<>(1);
        dialogsToUpdate.put(dialogId,0);
        NotificationsController.getInstance(currentAccount).processDialogsUpdateRead(dialogsToUpdate);
      }
 else {
        NotificationsController.getInstance(currentAccount).processReadMessages(null,dialogId,0,maxPositiveId,true);
        LongSparseArray<Integer> dialogsToUpdate=new LongSparseArray<>(1);
        dialogsToUpdate.put(dialogId,-1);
        NotificationsController.getInstance(currentAccount).processDialogsUpdateRead(dialogsToUpdate);
      }
    }
));
    createReadTask=maxPositiveId != Integer.MAX_VALUE;
  }
 else {
    if (maxDate == 0) {
      return;
    }
    createReadTask=true;
    TLRPC.EncryptedChat chat=getEncryptedChat(high_id);
    MessagesStorage.getInstance(currentAccount).processPendingRead(dialogId,maxPositiveId,maxNegativeId,maxDate,false);
    MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> AndroidUtilities.runOnUIThread(() -> {
      NotificationsController.getInstance(currentAccount).processReadMessages(null,dialogId,maxDate,0,popup);
      TLRPC.Dialog dialog=dialogs_dict.get(dialogId);
      if (dialog != null) {
        int prevCount=dialog.unread_count;
        if (countDiff == 0 || maxNegativeId <= dialog.top_message) {
          dialog.unread_count=0;
        }
 else {
          dialog.unread_count=Math.max(dialog.unread_count - countDiff,0);
          if (maxNegativeId != Integer.MAX_VALUE && dialog.unread_count > maxNegativeId - dialog.top_message) {
            dialog.unread_count=maxNegativeId - dialog.top_message;
          }
        }
        if (dialog.folder_id != 0) {
          TLRPC.Dialog folder=dialogs_dict.get(DialogObject.makeFolderDialogId(dialog.folder_id));
          if (folder != null) {
            if (countMessages) {
              if (isDialogMuted(dialog.id)) {
                folder.unread_count-=(prevCount - dialog.unread_count);
              }
 else {
                folder.unread_mentions_count-=(prevCount - dialog.unread_count);
              }
            }
 else             if (dialog.unread_count == 0) {
              if (isDialogMuted(dialog.id)) {
                folder.unread_count--;
              }
 else {
                folder.unread_mentions_count--;
              }
            }
          }
        }
        if ((prevCount != 0 || dialog.unread_mark) && dialog.unread_count == 0 && !isDialogMuted(dialogId)) {
          unreadUnmutedDialogs--;
        }
        if (dialog.unread_mark) {
          dialog.unread_mark=false;
          MessagesStorage.getInstance(currentAccount).setDialogUnread(dialog.id,false);
        }
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_READ_DIALOG_MESSAGE);
      }
      LongSparseArray<Integer> dialogsToUpdate=new LongSparseArray<>(1);
      dialogsToUpdate.put(dialogId,0);
      NotificationsController.getInstance(currentAccount).processDialogsUpdateRead(dialogsToUpdate);
    }
));
    if (chat != null && chat.ttl > 0) {
      int serverTime=Math.max(ConnectionsManager.getInstance(currentAccount).getCurrentTime(),maxDate);
      MessagesStorage.getInstance(currentAccount).createTaskForSecretChat(chat.id,serverTime,serverTime,0,null);
    }
  }
  if (createReadTask) {
    Utilities.stageQueue.postRunnable(() -> {
      ReadTask currentReadTask=readTasksMap.get(dialogId);
      if (currentReadTask == null) {
        currentReadTask=new ReadTask();
        currentReadTask.dialogId=dialogId;
        currentReadTask.sendRequestTime=SystemClock.elapsedRealtime() + 5000;
        if (!readNow) {
          readTasksMap.put(dialogId,currentReadTask);
          readTasks.add(currentReadTask);
        }
      }
      currentReadTask.maxDate=maxDate;
      currentReadTask.maxId=maxPositiveId;
      if (readNow) {
        completeReadTask(currentReadTask);
      }
    }
);
  }
}

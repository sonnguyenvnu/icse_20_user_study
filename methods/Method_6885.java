protected void completeDialogsReset(final TLRPC.messages_Dialogs dialogsRes,final int messagesCount,final int seq,final int newPts,final int date,final int qts,final LongSparseArray<TLRPC.Dialog> new_dialogs_dict,final LongSparseArray<MessageObject> new_dialogMessage,final TLRPC.Message lastMessage){
  Utilities.stageQueue.postRunnable(() -> {
    gettingDifference=false;
    MessagesStorage.getInstance(currentAccount).setLastPtsValue(newPts);
    MessagesStorage.getInstance(currentAccount).setLastDateValue(date);
    MessagesStorage.getInstance(currentAccount).setLastQtsValue(qts);
    getDifference();
    AndroidUtilities.runOnUIThread(() -> {
      resetingDialogs=false;
      applyDialogsNotificationsSettings(dialogsRes.dialogs);
      if (!UserConfig.getInstance(currentAccount).draftsLoaded) {
        DataQuery.getInstance(currentAccount).loadDrafts();
      }
      putUsers(dialogsRes.users,false);
      putChats(dialogsRes.chats,false);
      for (int a=0; a < allDialogs.size(); a++) {
        TLRPC.Dialog oldDialog=allDialogs.get(a);
        if (!DialogObject.isSecretDialogId(oldDialog.id)) {
          dialogs_dict.remove(oldDialog.id);
          MessageObject messageObject=dialogMessage.get(oldDialog.id);
          dialogMessage.remove(oldDialog.id);
          if (messageObject != null) {
            dialogMessagesByIds.remove(messageObject.getId());
            if (messageObject.messageOwner.random_id != 0) {
              dialogMessagesByRandomIds.remove(messageObject.messageOwner.random_id);
            }
          }
        }
      }
      for (int a=0; a < new_dialogs_dict.size(); a++) {
        long key=new_dialogs_dict.keyAt(a);
        TLRPC.Dialog value=new_dialogs_dict.valueAt(a);
        if (value.draft instanceof TLRPC.TL_draftMessage) {
          DataQuery.getInstance(currentAccount).saveDraft(value.id,value.draft,null,false);
        }
        dialogs_dict.put(key,value);
        MessageObject messageObject=new_dialogMessage.get(value.id);
        dialogMessage.put(key,messageObject);
        if (messageObject != null && messageObject.messageOwner.to_id.channel_id == 0) {
          dialogMessagesByIds.put(messageObject.getId(),messageObject);
          if (messageObject.messageOwner.random_id != 0) {
            dialogMessagesByRandomIds.put(messageObject.messageOwner.random_id,messageObject);
          }
        }
      }
      allDialogs.clear();
      for (int a=0, size=dialogs_dict.size(); a < size; a++) {
        allDialogs.add(dialogs_dict.valueAt(a));
      }
      sortDialogs(null);
      dialogsEndReached.put(0,true);
      serverDialogsEndReached.put(0,false);
      dialogsEndReached.put(1,true);
      serverDialogsEndReached.put(1,false);
      int totalDialogsLoadCount=UserConfig.getInstance(currentAccount).getTotalDialogsCount(0);
      int[] dialogsLoadOffset=UserConfig.getInstance(currentAccount).getDialogLoadOffsets(0);
      if (totalDialogsLoadCount < 400 && dialogsLoadOffset[UserConfig.i_dialogsLoadOffsetId] != -1 && dialogsLoadOffset[UserConfig.i_dialogsLoadOffsetId] != Integer.MAX_VALUE) {
        loadDialogs(0,100,0,false);
      }
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
    }
);
  }
);
}

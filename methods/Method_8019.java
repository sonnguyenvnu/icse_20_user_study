public void checkCurrentDialogIndex(boolean frozen){
  ArrayList<TLRPC.Dialog> dialogsArray=DialogsActivity.getDialogsArray(currentAccount,dialogsType,folderId,frozen);
  if (index < dialogsArray.size()) {
    TLRPC.Dialog dialog=dialogsArray.get(index);
    TLRPC.Dialog nextDialog=index + 1 < dialogsArray.size() ? dialogsArray.get(index + 1) : null;
    TLRPC.DraftMessage newDraftMessage=DataQuery.getInstance(currentAccount).getDraft(currentDialogId);
    MessageObject newMessageObject;
    if (currentDialogFolderId != 0) {
      newMessageObject=findFolderTopMessage();
    }
 else {
      newMessageObject=MessagesController.getInstance(currentAccount).dialogMessage.get(dialog.id);
    }
    if (currentDialogId != dialog.id || message != null && message.getId() != dialog.top_message || newMessageObject != null && newMessageObject.messageOwner.edit_date != currentEditDate || unreadCount != dialog.unread_count || mentionCount != dialog.unread_mentions_count || markUnread != dialog.unread_mark || message != newMessageObject || message == null && newMessageObject != null || newDraftMessage != draftMessage || drawPin != dialog.pinned) {
      boolean dialogChanged=currentDialogId != dialog.id;
      currentDialogId=dialog.id;
      if (dialog instanceof TLRPC.TL_dialogFolder) {
        TLRPC.TL_dialogFolder dialogFolder=(TLRPC.TL_dialogFolder)dialog;
        currentDialogFolderId=dialogFolder.folder.id;
      }
 else {
        currentDialogFolderId=0;
      }
      fullSeparator=dialog instanceof TLRPC.TL_dialog && dialog.pinned && nextDialog != null && !nextDialog.pinned;
      fullSeparator2=dialog instanceof TLRPC.TL_dialogFolder && nextDialog != null && !nextDialog.pinned;
      update(0);
      if (dialogChanged) {
        reorderIconProgress=drawPin && drawReorder ? 1.0f : 0.0f;
      }
      checkOnline();
    }
  }
}

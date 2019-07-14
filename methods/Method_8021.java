private MessageObject findFolderTopMessage(){
  ArrayList<TLRPC.Dialog> dialogs=DialogsActivity.getDialogsArray(currentAccount,dialogsType,currentDialogFolderId,false);
  MessageObject maxMessage=null;
  if (!dialogs.isEmpty()) {
    for (int a=0, N=dialogs.size(); a < N; a++) {
      TLRPC.Dialog dialog=dialogs.get(a);
      MessageObject object=MessagesController.getInstance(currentAccount).dialogMessage.get(dialog.id);
      if (object != null && (maxMessage == null || object.messageOwner.date > maxMessage.messageOwner.date)) {
        maxMessage=object;
      }
      if (dialog.pinnedNum == 0) {
        break;
      }
    }
  }
  return maxMessage;
}

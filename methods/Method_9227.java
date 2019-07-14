private void updateDialogIndices(){
  if (listView == null || listView.getAdapter() != dialogsAdapter) {
    return;
  }
  ArrayList<TLRPC.Dialog> dialogs=getDialogsArray(currentAccount,dialogsType,folderId,false);
  int count=listView.getChildCount();
  for (int a=0; a < count; a++) {
    View child=listView.getChildAt(a);
    if (child instanceof DialogCell) {
      DialogCell dialogCell=(DialogCell)child;
      TLRPC.Dialog dialog=getMessagesController().dialogs_dict.get(dialogCell.getDialogId());
      if (dialog == null) {
        continue;
      }
      int index=dialogs.indexOf(dialog);
      if (index < 0) {
        continue;
      }
      dialogCell.setDialogIndex(index);
    }
  }
}

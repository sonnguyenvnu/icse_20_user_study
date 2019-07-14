public static ArrayList<TLRPC.Dialog> getDialogsArray(int currentAccount,int dialogsType,int folderId,boolean frozen){
  if (frozen && frozenDialogsList != null) {
    return frozenDialogsList;
  }
  MessagesController messagesController=AccountInstance.getInstance(currentAccount).getMessagesController();
  if (dialogsType == 0) {
    return messagesController.getDialogs(folderId);
  }
 else   if (dialogsType == 1) {
    return messagesController.dialogsServerOnly;
  }
 else   if (dialogsType == 2) {
    return messagesController.dialogsCanAddUsers;
  }
 else   if (dialogsType == 3) {
    return messagesController.dialogsForward;
  }
 else   if (dialogsType == 4) {
    return messagesController.dialogsUsersOnly;
  }
 else   if (dialogsType == 5) {
    return messagesController.dialogsChannelsOnly;
  }
 else   if (dialogsType == 6) {
    return messagesController.dialogsGroupsOnly;
  }
  return null;
}

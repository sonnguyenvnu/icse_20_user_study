private TLRPC.TL_dialogFolder ensureFolderDialogExists(int folderId,boolean[] folderCreated){
  if (folderId == 0) {
    return null;
  }
  long folderDialogId=DialogObject.makeFolderDialogId(folderId);
  TLRPC.Dialog dialog=dialogs_dict.get(folderDialogId);
  if (dialog instanceof TLRPC.TL_dialogFolder) {
    if (folderCreated != null) {
      folderCreated[0]=false;
    }
    return (TLRPC.TL_dialogFolder)dialog;
  }
  if (folderCreated != null) {
    folderCreated[0]=true;
  }
  TLRPC.TL_dialogFolder dialogFolder=new TLRPC.TL_dialogFolder();
  dialogFolder.id=folderDialogId;
  dialogFolder.peer=new TLRPC.TL_peerUser();
  dialogFolder.folder=new TLRPC.TL_folder();
  dialogFolder.folder.id=folderId;
  dialogFolder.folder.title=LocaleController.getString("ArchivedChats",R.string.ArchivedChats);
  dialogFolder.pinned=true;
  int maxPinnedNum=0;
  for (int a=0; a < allDialogs.size(); a++) {
    TLRPC.Dialog d=allDialogs.get(a);
    if (!d.pinned) {
      break;
    }
    maxPinnedNum=Math.max(d.pinnedNum,maxPinnedNum);
  }
  dialogFolder.pinnedNum=maxPinnedNum + 1;
  TLRPC.TL_messages_dialogs dialogs=new TLRPC.TL_messages_dialogs();
  dialogs.dialogs.add(dialogFolder);
  MessagesStorage.getInstance(currentAccount).putDialogs(dialogs,1);
  dialogs_dict.put(folderDialogId,dialogFolder);
  allDialogs.add(0,dialogFolder);
  return dialogFolder;
}

private void addDialogToItsFolder(int index,TLRPC.Dialog dialog,boolean countMessages){
  int folderId;
  if (dialog instanceof TLRPC.TL_dialogFolder) {
    folderId=0;
    dialog.unread_count=0;
    dialog.unread_mentions_count=0;
  }
 else {
    folderId=dialog.folder_id;
  }
  ArrayList<TLRPC.Dialog> dialogs=dialogsByFolder.get(folderId);
  if (dialogs == null) {
    dialogs=new ArrayList<>();
    dialogsByFolder.put(folderId,dialogs);
  }
  if (folderId != 0 && dialog.unread_count != 0) {
    TLRPC.Dialog folder=dialogs_dict.get(DialogObject.makeFolderDialogId(folderId));
    if (folder != null) {
      if (countMessages) {
        if (isDialogMuted(dialog.id)) {
          folder.unread_count+=dialog.unread_count;
        }
 else {
          folder.unread_mentions_count+=dialog.unread_count;
        }
      }
 else {
        if (isDialogMuted(dialog.id)) {
          folder.unread_count++;
        }
 else {
          folder.unread_mentions_count++;
        }
      }
    }
  }
  if (index == -1) {
    dialogs.add(dialog);
  }
 else   if (index == -2) {
    if (dialogs.isEmpty() || !(dialogs.get(0) instanceof TLRPC.TL_dialogFolder)) {
      dialogs.add(0,dialog);
    }
 else {
      dialogs.add(1,dialog);
    }
  }
 else {
    dialogs.add(index,dialog);
  }
}

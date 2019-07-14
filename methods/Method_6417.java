public static void initDialog(TLRPC.Dialog dialog){
  if (dialog == null || dialog.id != 0) {
    return;
  }
  if (dialog instanceof TLRPC.TL_dialog) {
    if (dialog.peer == null) {
      return;
    }
    if (dialog.peer.user_id != 0) {
      dialog.id=dialog.peer.user_id;
    }
 else     if (dialog.peer.chat_id != 0) {
      dialog.id=-dialog.peer.chat_id;
    }
 else {
      dialog.id=-dialog.peer.channel_id;
    }
  }
 else   if (dialog instanceof TLRPC.TL_dialogFolder) {
    TLRPC.TL_dialogFolder dialogFolder=(TLRPC.TL_dialogFolder)dialog;
    dialog.id=makeFolderDialogId(dialogFolder.folder.id);
  }
}

private int getPinnedCount(){
  int pinnedCount=0;
  ArrayList<TLRPC.Dialog> dialogs=getMessagesController().getDialogs(folderId);
  for (int a=0, N=dialogs.size(); a < N; a++) {
    TLRPC.Dialog dialog=dialogs.get(a);
    if (dialog instanceof TLRPC.TL_dialogFolder) {
      continue;
    }
    int lower_id=(int)dialog.id;
    if (dialog.pinned) {
      pinnedCount++;
    }
 else {
      break;
    }
  }
  return pinnedCount;
}

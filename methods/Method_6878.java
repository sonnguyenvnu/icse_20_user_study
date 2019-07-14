protected void onFolderEmpty(int folderId){
  int[] dialogsLoadOffset=UserConfig.getInstance(currentAccount).getDialogLoadOffsets(folderId);
  if (dialogsLoadOffset[UserConfig.i_dialogsLoadOffsetId] == Integer.MAX_VALUE) {
    removeFolder(folderId);
  }
 else {
    loadDialogs(folderId,0,10,false,() -> removeFolder(folderId));
  }
}

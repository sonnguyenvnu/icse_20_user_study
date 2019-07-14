private boolean hasHiddenArchive(){
  return listView.getAdapter() == dialogsAdapter && !onlySelect && dialogsType == 0 && folderId == 0 && getMessagesController().hasHiddenArchive();
}

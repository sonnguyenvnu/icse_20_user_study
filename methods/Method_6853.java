public boolean hasHiddenArchive(){
  return SharedConfig.archiveHidden && dialogs_dict.get(DialogObject.makeFolderDialogId(1)) != null;
}

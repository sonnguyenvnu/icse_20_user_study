public boolean isPinnedDialogsLoaded(int folderId){
  return getPreferences().getBoolean("2pinnedDialogsLoaded" + folderId,false);
}

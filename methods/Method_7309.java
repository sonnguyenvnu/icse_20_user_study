public int getTotalDialogsCount(int folderId){
  return getPreferences().getInt("2totalDialogsLoadCount" + (folderId == 0 ? "" : folderId),0);
}

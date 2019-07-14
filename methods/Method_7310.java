public void setTotalDialogsCount(int folderId,int totalDialogsLoadCount){
  getPreferences().edit().putInt("2totalDialogsLoadCount" + (folderId == 0 ? "" : folderId),totalDialogsLoadCount).commit();
}

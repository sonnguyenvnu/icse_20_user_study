@Override public boolean onFragmentCreate(){
  super.onFragmentCreate();
  rowCount=0;
  keepMediaRow=rowCount++;
  keepMediaInfoRow=rowCount++;
  cacheRow=rowCount++;
  cacheInfoRow=rowCount++;
  databaseRow=rowCount++;
  databaseInfoRow=rowCount++;
  databaseSize=MessagesStorage.getInstance(currentAccount).getDatabaseSize();
  Utilities.globalQueue.postRunnable(() -> {
    cacheSize=getDirectorySize(FileLoader.checkDirectory(FileLoader.MEDIA_DIR_CACHE),0);
    if (canceled) {
      return;
    }
    photoSize=getDirectorySize(FileLoader.checkDirectory(FileLoader.MEDIA_DIR_IMAGE),0);
    if (canceled) {
      return;
    }
    videoSize=getDirectorySize(FileLoader.checkDirectory(FileLoader.MEDIA_DIR_VIDEO),0);
    if (canceled) {
      return;
    }
    documentsSize=getDirectorySize(FileLoader.checkDirectory(FileLoader.MEDIA_DIR_DOCUMENT),1);
    if (canceled) {
      return;
    }
    musicSize=getDirectorySize(FileLoader.checkDirectory(FileLoader.MEDIA_DIR_DOCUMENT),2);
    if (canceled) {
      return;
    }
    audioSize=getDirectorySize(FileLoader.checkDirectory(FileLoader.MEDIA_DIR_AUDIO),0);
    totalSize=cacheSize + videoSize + audioSize + photoSize + documentsSize + musicSize;
    AndroidUtilities.runOnUIThread(() -> {
      calculating=false;
      if (listAdapter != null) {
        listAdapter.notifyDataSetChanged();
      }
    }
);
  }
);
  return true;
}

private void purgeByMessageBox(Integer msgBoxId){
  if (LOCAL_LOGV) {
    Timber.v("Purge cache in message box: " + msgBoxId);
  }
  if (msgBoxId != null) {
    HashSet<Uri> msgBox=mMessageBoxes.remove(msgBoxId);
    if (msgBox != null) {
      for (      Uri key : msgBox) {
        mUpdating.remove(key);
        PduCacheEntry entry=super.purge(key);
        if (entry != null) {
          removeFromThreads(key,entry);
        }
      }
    }
  }
}

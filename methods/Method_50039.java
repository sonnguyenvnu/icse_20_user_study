private void removeFromMessageBoxes(Uri key,PduCacheEntry entry){
  HashSet<Uri> msgBox=mThreads.get(Long.valueOf(entry.getMessageBox()));
  if (msgBox != null) {
    msgBox.remove(key);
  }
}

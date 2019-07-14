private void purgeByThreadId(long threadId){
  if (LOCAL_LOGV) {
    Timber.v("Purge cache in thread: " + threadId);
  }
  HashSet<Uri> thread=mThreads.remove(threadId);
  if (thread != null) {
    for (    Uri key : thread) {
      mUpdating.remove(key);
      PduCacheEntry entry=super.purge(key);
      if (entry != null) {
        removeFromMessageBoxes(key,entry);
      }
    }
  }
}

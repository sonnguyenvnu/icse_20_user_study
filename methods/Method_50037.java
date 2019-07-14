private void removeFromThreads(Uri key,PduCacheEntry entry){
  HashSet<Uri> thread=mThreads.get(entry.getThreadId());
  if (thread != null) {
    thread.remove(key);
  }
}

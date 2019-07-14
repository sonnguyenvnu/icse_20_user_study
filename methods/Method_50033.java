@Override synchronized public boolean put(Uri uri,PduCacheEntry entry){
  int msgBoxId=entry.getMessageBox();
  HashSet<Uri> msgBox=mMessageBoxes.get(msgBoxId);
  if (msgBox == null) {
    msgBox=new HashSet<Uri>();
    mMessageBoxes.put(msgBoxId,msgBox);
  }
  long threadId=entry.getThreadId();
  HashSet<Uri> thread=mThreads.get(threadId);
  if (thread == null) {
    thread=new HashSet<Uri>();
    mThreads.put(threadId,thread);
  }
  Uri finalKey=normalizeKey(uri);
  boolean result=super.put(finalKey,entry);
  if (result) {
    msgBox.add(finalKey);
    thread.add(finalKey);
  }
  setUpdating(uri,false);
  return result;
}

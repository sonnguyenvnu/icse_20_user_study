@Override public void sync(){
  HoodieTimeline oldTimeline=getTimeline();
  HoodieTimeline newTimeline=metaClient.reloadActiveTimeline().filterCompletedAndCompactionInstants();
  try {
    writeLock.lock();
    runSync(oldTimeline,newTimeline);
  }
  finally {
    writeLock.unlock();
  }
}

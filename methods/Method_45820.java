@Override public int getAllProviderSize(){
  rLock.lock();
  try {
    return directUrlGroup.size() + registryGroup.size();
  }
  finally {
    rLock.unlock();
  }
}

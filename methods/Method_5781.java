private static synchronized boolean lockFolder(File cacheDir){
  if (cacheFolderLockingDisabled) {
    return true;
  }
  return lockedCacheDirs.add(cacheDir.getAbsoluteFile());
}

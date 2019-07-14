private static synchronized void unlockFolder(File cacheDir){
  if (!cacheFolderLockingDisabled) {
    lockedCacheDirs.remove(cacheDir.getAbsoluteFile());
  }
}

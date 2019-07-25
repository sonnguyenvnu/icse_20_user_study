/** 
 * Commit the changes. <p> This method does nothing if there are no unsaved changes, otherwise it increments the current version and stores the data (for file based stores). <p> It is not necessary to call this method when auto-commit is enabled (the default setting), as in this case it is automatically called from time to time or when enough changes have accumulated. However, it may still be called to flush all changes to disk. <p> At most one store operation may run at any time.
 * @return the new version (incremented if there were changes)
 */
public long commit(){
  if (!storeLock.isHeldByCurrentThread() || currentStoreVersion < 0) {
    storeLock.lock();
    try {
      store();
    }
  finally {
      storeLock.unlock();
    }
  }
  return currentVersion;
}

/** 
 * Add a page to the free list.
 * @param pageId the page id
 * @param undo if the undo record must have been written
 */
void free(int pageId,boolean undo){
  if (trace.isDebugEnabled()) {
  }
  cache.remove(pageId);
  if (SysProperties.CHECK && !recoveryRunning && undo) {
    if (logMode != LOG_MODE_OFF) {
      log.addUndo(pageId,null);
    }
  }
  freePage(pageId);
  if (recoveryRunning) {
    writePage(pageId,createData());
    if (reservedPages != null && reservedPages.containsKey(pageId)) {
      int latestPos=reservedPages.get(pageId);
      if (latestPos > log.getLogPos()) {
        allocatePage(pageId);
      }
    }
  }
}

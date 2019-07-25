/** 
 * Update a page.
 * @param page the page
 */
public synchronized void update(Page page){
  if (trace.isDebugEnabled()) {
    if (!page.isChanged()) {
      trace.debug("updateRecord " + page.toString());
    }
  }
  checkOpen();
  database.checkWritingAllowed();
  page.setChanged(true);
  int pos=page.getPos();
  if (SysProperties.CHECK && !recoveryRunning) {
    if (logMode != LOG_MODE_OFF) {
      log.addUndo(pos,null);
    }
  }
  allocatePage(pos);
  cache.update(pos,page);
}

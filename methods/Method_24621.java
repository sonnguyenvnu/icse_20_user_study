/** 
 * Clear breakpoints in a specific tab.
 * @param tabFilename the tab's file name
 */
synchronized void clearBreakpoints(String tabFilename){
  if (isBusy()) {
    log("busy");
    return;
  }
  Iterator<LineBreakpoint> i=breakpoints.iterator();
  while (i.hasNext()) {
    LineBreakpoint bp=i.next();
    if (bp.lineID().fileName().equals(tabFilename)) {
      bp.remove();
      i.remove();
    }
  }
}

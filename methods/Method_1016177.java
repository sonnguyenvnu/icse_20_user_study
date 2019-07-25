/** 
 * Sets active pane.
 * @param paneData new active pane
 */
protected void activate(final PaneData<T> paneData){
  if (paneData != null && paneData != activePane) {
    activePane=paneData;
    checkSelection();
  }
}

/** 
 * show/hide the ripple overlay NOTE: setting overlay visibility to false will reset forceOverlay to false
 * @param visible
 */
public void setOverlayVisible(boolean visible){
  if (visible) {
    showOverlay();
  }
 else {
    forceOverlay=!visible ? false : forceOverlay;
    hideOverlay();
  }
}

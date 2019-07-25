/** 
 * Set full-screen mode. <p> Setting the display into or out of full-screen mode is delegated to the currently set  {@link FullScreenStrategy}, which may be <code>null</code> (in which case full-screen mode is not supported).
 * @param fullScreen true for full-screen, otherwise false
 */
public void set(boolean fullScreen){
  if (fullScreenStrategy != null) {
    if (fullScreen) {
      fullScreenStrategy.enterFullScreenMode();
    }
 else {
      fullScreenStrategy.exitFullScreenMode();
    }
  }
}

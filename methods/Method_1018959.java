/** 
 * Toggle whether the video display is in full-screen or not. <p> Setting the display into or out of full-screen mode is delegated to the currently set  {@link FullScreenStrategy}, which may be <code>null</code> (in which case full-screen mode is not supported).
 */
public void toggle(){
  if (fullScreenStrategy != null) {
    set(!fullScreenStrategy.isFullScreenMode());
  }
}

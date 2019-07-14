/** 
 * this method will be set to private in future versions of JFoenix, user the method  {@link #setOverlayVisible(boolean)}
 */
@Deprecated public void showOverlay(){
  if (rippler.overlayRect != null) {
    rippler.overlayRect.outAnimation.stop();
  }
  rippler.createOverlay();
  rippler.overlayRect.inAnimation.play();
}

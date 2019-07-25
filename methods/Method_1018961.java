/** 
 * Set a new overlay component. <p> The existing overlay if there is one will be disabled. <p> The new overlay will <strong>not</strong> automatically be enabled. <p> The overlay should be a sub-class of <code>Window</code> or <code>JWindow</code>. If your overlay contains dynamically updated content such as a timer or animated graphics, then you should use <code>JWindow</code> so that your updates will be double-buffered and there will be no tearing or flickering when you paint the overlay. If you do this, you must take care to erase the overlay background before you paint it. <p> <strong>When the overlay is no longer needed it is your responsibility to  {@link Window#dispose()} it - if you donot do this you may leak resources. If you set multiple different overlays you must remember to dispose the old overlay.</strong> <p> It is recommended to set the overlay once as early as possible in your application.
 * @param overlay overlay component, may be <code>null</code>
 */
public void set(Window overlay){
  if (mediaPlayer.videoSurface().getVideoSurface() instanceof ComponentVideoSurface) {
    enable(false);
    removeOverlay();
    addOverlay(overlay);
  }
 else {
    throw new IllegalStateException("Overlay requires a ComponentVideoSurface");
  }
}

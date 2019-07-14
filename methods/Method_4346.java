/** 
 * Notifies the media clock that a renderer has been disabled. Stops using the media clock of this renderer if used.
 * @param renderer The renderer which has been disabled.
 */
public void onRendererDisabled(Renderer renderer){
  if (renderer == rendererClockSource) {
    this.rendererClock=null;
    this.rendererClockSource=null;
  }
}

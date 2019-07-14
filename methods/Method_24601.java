/** 
 * Notify this line highlight that it is no longer used. Call this for cleanup before the  {@link LineHighlight} is discarded.
 */
public void dispose(){
  lineID.removeListener(this);
  lineID.stopTracking();
  allHighlights.remove(this);
}

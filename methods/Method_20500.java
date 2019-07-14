/** 
 * Find the area of the source file that is currently visible on screen, taking into account the current scale, translation, orientation and clipped region. This is a convenience method; see {@link #viewToFileRect(Rect,Rect)}.
 * @param fRect rectangle instance to which the result will be written. Re-use for efficiency.
 */
public void visibleFileRect(Rect fRect){
  if (vTranslate == null || !readySent) {
    return;
  }
  fRect.set(0,0,getWidth(),getHeight());
  viewToFileRect(fRect,fRect);
}

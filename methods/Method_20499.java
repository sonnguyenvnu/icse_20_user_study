/** 
 * Converts a rectangle within the view to the corresponding rectangle from the source file, taking into account the current scale, translation, orientation and clipped region. This can be used to decode a bitmap from the source file. This method will only work when the image has fully initialised, after  {@link #isReady()} returnstrue. It is not guaranteed to work with preloaded bitmaps. The result is written to the fRect argument. Re-use a single instance for efficiency.
 * @param vRect rectangle representing the view area to interpret.
 * @param fRect rectangle instance to which the result will be written. Re-use for efficiency.
 */
public void viewToFileRect(Rect vRect,Rect fRect){
  if (vTranslate == null || !readySent) {
    return;
  }
  fRect.set((int)viewToSourceX(vRect.left),(int)viewToSourceY(vRect.top),(int)viewToSourceX(vRect.right),(int)viewToSourceY(vRect.bottom));
  fileSRect(fRect,fRect);
  fRect.set(Math.max(0,fRect.left),Math.max(0,fRect.top),Math.min(sWidth,fRect.right),Math.min(sHeight,fRect.bottom));
  if (sRegion != null) {
    fRect.offset(sRegion.left,sRegion.top);
  }
}

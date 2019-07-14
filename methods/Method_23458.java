/** 
 * @webref shape:vertex
 * @brief Ends a contour
 * @see PShape#beginContour()
 */
public void endContour(){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"endContour()");
    return;
  }
  if (family == GROUP) {
    PGraphics.showWarning("Cannot end contour in GROUP shapes");
    return;
  }
  if (!openContour) {
    PGraphics.showWarning("Need to call beginContour() first.");
    return;
  }
  endContourImpl();
  openContour=false;
}

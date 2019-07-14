@Override public void endContour(){
  if (!openContour) {
    PGraphics.showWarning(NO_BEGIN_CONTOUR_ERROR);
    return;
  }
  openContour=false;
}

@Override public void endContour(){
  if (!openContour) {
    PGraphics.showWarning("Need to call beginContour() first");
    return;
  }
  if (gpath != null)   gpath.closePath();
  GeneralPath contourPath=gpath;
  gpath=auxPath;
  auxPath=contourPath;
  openContour=false;
}

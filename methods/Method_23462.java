public void strokeCap(int cap){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"strokeCap()");
    return;
  }
  strokeCap=cap;
}

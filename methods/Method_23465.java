public void noStroke(){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"noStroke()");
    return;
  }
  stroke=false;
}

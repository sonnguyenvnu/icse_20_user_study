public void tint(int rgb){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"tint()");
    return;
  }
  tint=true;
  colorCalc(rgb);
  tintColor=calcColor;
}

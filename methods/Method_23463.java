public void fill(int rgb){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"fill()");
    return;
  }
  fill=true;
  colorCalc(rgb);
  fillColor=calcColor;
  if (!setAmbient) {
    ambientColor=fillColor;
  }
}

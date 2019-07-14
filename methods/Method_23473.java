public void emissive(int rgb){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"emissive()");
    return;
  }
  colorCalc(rgb);
  emissiveColor=calcColor;
}

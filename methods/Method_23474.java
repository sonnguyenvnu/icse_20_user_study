public void emissive(float gray){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"emissive()");
    return;
  }
  colorCalc(gray);
  emissiveColor=calcColor;
}

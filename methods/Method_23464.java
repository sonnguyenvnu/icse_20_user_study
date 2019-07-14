public void fill(int rgb,float alpha){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"fill()");
    return;
  }
  fill=true;
  colorCalc(rgb,alpha);
  fillColor=calcColor;
  if (!setAmbient) {
    ambientColor=fillColor;
  }
}

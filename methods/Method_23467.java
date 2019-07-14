public void tint(int rgb,float alpha){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"tint()");
    return;
  }
  tint=true;
  colorCalc(rgb,alpha);
  tintColor=calcColor;
}

public void tint(float x,float y,float z,float alpha){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"tint()");
    return;
  }
  tint=true;
  colorCalc(x,y,z,alpha);
  tintColor=calcColor;
}

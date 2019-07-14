public void specular(float x,float y,float z){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"specular()");
    return;
  }
  colorCalc(x,y,z);
  specularColor=calcColor;
}

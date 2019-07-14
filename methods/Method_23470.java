public void specular(int rgb){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"specular()");
    return;
  }
  colorCalc(rgb);
  specularColor=calcColor;
}

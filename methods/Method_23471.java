public void specular(float gray){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"specular()");
    return;
  }
  colorCalc(gray);
  specularColor=calcColor;
}

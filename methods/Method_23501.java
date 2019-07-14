public void setTexture(PImage tex){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setTexture()");
    return;
  }
  image=tex;
}

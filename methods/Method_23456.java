public void texture(PImage tex){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"texture()");
    return;
  }
  image=tex;
}

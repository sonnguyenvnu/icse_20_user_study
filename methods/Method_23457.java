public void noTexture(){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"noTexture()");
    return;
  }
  image=null;
}

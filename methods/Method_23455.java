public void textureMode(int mode){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"textureMode()");
    return;
  }
  textureMode=mode;
}

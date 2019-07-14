public void setTint(boolean tint){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setTint()");
    return;
  }
  this.tint=tint;
}

public void endShape(int mode){
  if (family == GROUP) {
    PGraphics.showWarning("Cannot end GROUP shape");
    return;
  }
  if (!openShape) {
    PGraphics.showWarning("Need to call beginShape() first");
    return;
  }
  close=(mode == CLOSE);
  openShape=false;
}

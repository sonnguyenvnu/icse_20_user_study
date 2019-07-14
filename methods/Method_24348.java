@Override public void setFill(boolean fill){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setFill()");
    return;
  }
  if (family == GROUP) {
    for (int i=0; i < childCount; i++) {
      PShapeOpenGL child=(PShapeOpenGL)children[i];
      child.setFill(fill);
    }
  }
 else   if (this.fill != fill) {
    markForTessellation();
  }
  this.fill=fill;
}

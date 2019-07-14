@Override public void setStrokeCap(int cap){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setStrokeCap()");
    return;
  }
  if (family == GROUP) {
    for (int i=0; i < childCount; i++) {
      PShapeOpenGL child=(PShapeOpenGL)children[i];
      child.setStrokeCap(cap);
    }
  }
 else {
    if (is2D() && strokeCap != cap) {
      markForTessellation();
    }
    strokeCap=cap;
  }
}

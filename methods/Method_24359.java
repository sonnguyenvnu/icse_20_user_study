@Override public void setStrokeJoin(int join){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setStrokeJoin()");
    return;
  }
  if (family == GROUP) {
    for (int i=0; i < childCount; i++) {
      PShapeOpenGL child=(PShapeOpenGL)children[i];
      child.setStrokeJoin(join);
    }
  }
 else {
    if (is2D() && strokeJoin != join) {
      markForTessellation();
    }
    strokeJoin=join;
  }
}

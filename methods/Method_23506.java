public void setTint(int fill){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setTint()");
    return;
  }
  tintColor=fill;
  if (vertices != null) {
    for (int i=0; i < vertices.length; i++) {
      setFill(i,fill);
    }
  }
}

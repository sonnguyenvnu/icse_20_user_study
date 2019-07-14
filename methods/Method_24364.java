@Override public void setShininess(float shininess){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setShininess()");
    return;
  }
  if (family == GROUP) {
    for (int i=0; i < childCount; i++) {
      PShapeOpenGL child=(PShapeOpenGL)children[i];
      child.setShininess(shininess);
    }
  }
 else {
    setShininessImpl(shininess);
  }
}

@Override public void solid(boolean solid){
  if (family == GROUP) {
    for (int i=0; i < childCount; i++) {
      PShapeOpenGL child=(PShapeOpenGL)children[i];
      child.solid(solid);
    }
  }
 else {
    this.solid=solid;
  }
}

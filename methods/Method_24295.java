protected void updateRoot(PShape root){
  this.root=(PShapeOpenGL)root;
  if (family == GROUP) {
    for (int i=0; i < childCount; i++) {
      PShapeOpenGL child=(PShapeOpenGL)children[i];
      child.updateRoot(root);
    }
  }
}

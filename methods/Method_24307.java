protected void untexChild(boolean newValue,PShapeOpenGL caller){
  if (untexChild == newValue)   return;
  if (newValue) {
    untexChild=true;
  }
 else {
    untexChild=false;
    for (int i=0; i < childCount; i++) {
      PShapeOpenGL child=(PShapeOpenGL)children[i];
      if (child == caller)       continue;
      if (!child.hasTexture()) {
        untexChild=true;
        break;
      }
    }
  }
  if (parent != null) {
    ((PShapeOpenGL)parent).untexChild(newValue,this);
  }
}

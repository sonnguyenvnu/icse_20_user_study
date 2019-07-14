protected void strokedTexture(boolean newValue,PShapeOpenGL caller){
  if (strokedTexture == newValue)   return;
  if (newValue) {
    strokedTexture=true;
  }
 else {
    strokedTexture=false;
    for (int i=0; i < childCount; i++) {
      PShapeOpenGL child=(PShapeOpenGL)children[i];
      if (child == caller)       continue;
      if (child.hasStrokedTexture()) {
        strokedTexture=true;
        break;
      }
    }
  }
  if (parent != null) {
    ((PShapeOpenGL)parent).strokedTexture(newValue,this);
  }
}

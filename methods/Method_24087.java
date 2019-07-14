protected PGL getPrimaryPGL(){
  if (primaryGraphics) {
    return pgl;
  }
 else {
    return ((PGraphicsOpenGL)parent.g).pgl;
  }
}

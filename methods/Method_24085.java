protected PGraphicsOpenGL getPrimaryPG(){
  if (primaryGraphics) {
    return this;
  }
 else {
    return (PGraphicsOpenGL)parent.g;
  }
}

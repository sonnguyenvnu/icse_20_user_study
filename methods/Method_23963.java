protected int getReadFramebuffer(){
  return fboLayerEnabled ? glColorFbo.get(0) : 0;
}

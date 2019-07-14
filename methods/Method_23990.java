protected float getDepthValue(int scrX,int scrY){
  if (depthBuffer == null) {
    depthBuffer=FloatBuffer.allocate(1);
  }
  depthBuffer.rewind();
  readPixels(scrX,graphics.height - scrY - 1,1,1,DEPTH_COMPONENT,FLOAT,depthBuffer);
  return depthBuffer.get(0);
}

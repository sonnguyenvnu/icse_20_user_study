protected void beginOnscreenDraw(){
  updatePixelSize();
  pgl.beginRender();
  if (drawFramebuffer == null) {
    drawFramebuffer=new FrameBuffer(this,pixelWidth,pixelHeight,true);
  }
  drawFramebuffer.setFBO(pgl.getDrawFramebuffer());
  if (readFramebuffer == null) {
    readFramebuffer=new FrameBuffer(this,pixelWidth,pixelHeight,true);
  }
  readFramebuffer.setFBO(pgl.getReadFramebuffer());
  if (currentFramebuffer == null) {
    setFramebuffer(drawFramebuffer);
  }
  if (pgl.isFBOBacked()) {
    texture=pgl.wrapBackTexture(texture);
    ptexture=pgl.wrapFrontTexture(ptexture);
  }
}

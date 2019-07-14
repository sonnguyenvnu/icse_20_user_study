protected void copyTexture(Texture tex,int x,int y,int w,int h,boolean scale){
  if (tex == null) {
    throw new RuntimeException("Source texture is null");
  }
  if (tempFbo == null) {
    tempFbo=new FrameBuffer(pg,glWidth,glHeight);
  }
  tempFbo.setColorBuffer(this);
  tempFbo.disableDepthTest();
  pg.pushFramebuffer();
  pg.setFramebuffer(tempFbo);
  pg.pushStyle();
  pg.blendMode(REPLACE);
  if (scale) {
    pgl.drawTexture(tex.glTarget,tex.glName,tex.glWidth,tex.glHeight,0,0,tempFbo.width,tempFbo.height,1,x,y,x + w,y + h,0,0,width,height);
  }
 else {
    pgl.drawTexture(tex.glTarget,tex.glName,tex.glWidth,tex.glHeight,0,0,tempFbo.width,tempFbo.height,1,x,y,x + w,y + h,x,y,x + w,y + h);
  }
  pgl.flush();
  pg.popStyle();
  pg.popFramebuffer();
  updateTexels(x,y,w,h);
}

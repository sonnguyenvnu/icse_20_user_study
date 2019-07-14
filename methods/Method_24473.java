protected void copyTexture(int texTarget,int texName,int texWidth,int texHeight,int x,int y,int w,int h,boolean scale){
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
    pgl.drawTexture(texTarget,texName,texWidth,texHeight,0,0,tempFbo.width,tempFbo.height,x,y,w,h,0,0,width,height);
  }
 else {
    pgl.drawTexture(texTarget,texName,texWidth,texHeight,0,0,tempFbo.width,tempFbo.height,x,y,w,h,x,y,w,h);
  }
  pgl.flush();
  pg.popStyle();
  pg.popFramebuffer();
  updateTexels(x,y,w,h);
}

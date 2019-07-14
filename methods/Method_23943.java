protected void initDepthBuffer(){
  if (screenFb)   return;
  if (width == 0 || height == 0) {
    throw new RuntimeException("PFramebuffer: size undefined.");
  }
  pg.pushFramebuffer();
  pg.setFramebuffer(this);
  pgl.bindRenderbuffer(PGL.RENDERBUFFER,glDepth);
  int glConst=PGL.DEPTH_COMPONENT16;
  if (depthBits == 16) {
    glConst=PGL.DEPTH_COMPONENT16;
  }
 else   if (depthBits == 24) {
    glConst=PGL.DEPTH_COMPONENT24;
  }
 else   if (depthBits == 32) {
    glConst=PGL.DEPTH_COMPONENT32;
  }
  if (multisample) {
    pgl.renderbufferStorageMultisample(PGL.RENDERBUFFER,nsamples,glConst,width,height);
  }
 else {
    pgl.renderbufferStorage(PGL.RENDERBUFFER,glConst,width,height);
  }
  pgl.framebufferRenderbuffer(PGL.FRAMEBUFFER,PGL.DEPTH_ATTACHMENT,PGL.RENDERBUFFER,glDepth);
  pg.popFramebuffer();
}

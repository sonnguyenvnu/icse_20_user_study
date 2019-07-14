protected void initPackedDepthStencilBuffer(){
  if (screenFb)   return;
  if (width == 0 || height == 0) {
    throw new RuntimeException("PFramebuffer: size undefined.");
  }
  pg.pushFramebuffer();
  pg.setFramebuffer(this);
  pgl.bindRenderbuffer(PGL.RENDERBUFFER,glDepthStencil);
  if (multisample) {
    pgl.renderbufferStorageMultisample(PGL.RENDERBUFFER,nsamples,PGL.DEPTH24_STENCIL8,width,height);
  }
 else {
    pgl.renderbufferStorage(PGL.RENDERBUFFER,PGL.DEPTH24_STENCIL8,width,height);
  }
  pgl.framebufferRenderbuffer(PGL.FRAMEBUFFER,PGL.DEPTH_ATTACHMENT,PGL.RENDERBUFFER,glDepthStencil);
  pgl.framebufferRenderbuffer(PGL.FRAMEBUFFER,PGL.STENCIL_ATTACHMENT,PGL.RENDERBUFFER,glDepthStencil);
  pg.popFramebuffer();
}

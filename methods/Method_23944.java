protected void initStencilBuffer(){
  if (screenFb)   return;
  if (width == 0 || height == 0) {
    throw new RuntimeException("PFramebuffer: size undefined.");
  }
  pg.pushFramebuffer();
  pg.setFramebuffer(this);
  pgl.bindRenderbuffer(PGL.RENDERBUFFER,glStencil);
  int glConst=PGL.STENCIL_INDEX1;
  if (stencilBits == 1) {
    glConst=PGL.STENCIL_INDEX1;
  }
 else   if (stencilBits == 4) {
    glConst=PGL.STENCIL_INDEX4;
  }
 else   if (stencilBits == 8) {
    glConst=PGL.STENCIL_INDEX8;
  }
  if (multisample) {
    pgl.renderbufferStorageMultisample(PGL.RENDERBUFFER,nsamples,glConst,width,height);
  }
 else {
    pgl.renderbufferStorage(PGL.RENDERBUFFER,glConst,width,height);
  }
  pgl.framebufferRenderbuffer(PGL.FRAMEBUFFER,PGL.STENCIL_ATTACHMENT,PGL.RENDERBUFFER,glStencil);
  pg.popFramebuffer();
}

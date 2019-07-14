protected void initColorBufferMultisample(){
  if (screenFb)   return;
  pg.pushFramebuffer();
  pg.setFramebuffer(this);
  pgl.bindRenderbuffer(PGL.RENDERBUFFER,glMultisample);
  pgl.renderbufferStorageMultisample(PGL.RENDERBUFFER,nsamples,PGL.RGBA8,width,height);
  pgl.framebufferRenderbuffer(PGL.FRAMEBUFFER,PGL.COLOR_ATTACHMENT0,PGL.RENDERBUFFER,glMultisample);
  pg.popFramebuffer();
}

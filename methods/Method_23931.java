public void copy(FrameBuffer dest,int mask){
  pgl.bindFramebufferImpl(PGL.READ_FRAMEBUFFER,this.glFbo);
  pgl.bindFramebufferImpl(PGL.DRAW_FRAMEBUFFER,dest.glFbo);
  pgl.blitFramebuffer(0,0,this.width,this.height,0,0,dest.width,dest.height,mask,PGL.NEAREST);
  pgl.bindFramebufferImpl(PGL.READ_FRAMEBUFFER,pg.getCurrentFB().glFbo);
  pgl.bindFramebufferImpl(PGL.DRAW_FRAMEBUFFER,pg.getCurrentFB().glFbo);
}

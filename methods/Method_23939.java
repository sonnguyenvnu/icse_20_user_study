public void swapColorBuffers(){
  for (int i=0; i < numColorBuffers - 1; i++) {
    int i1=(i + 1);
    Texture tmp=colorBufferTex[i];
    colorBufferTex[i]=colorBufferTex[i1];
    colorBufferTex[i1]=tmp;
  }
  pg.pushFramebuffer();
  pg.setFramebuffer(this);
  for (int i=0; i < numColorBuffers; i++) {
    pgl.framebufferTexture2D(PGL.FRAMEBUFFER,PGL.COLOR_ATTACHMENT0 + i,colorBufferTex[i].glTarget,colorBufferTex[i].glName,0);
  }
  pgl.validateFramebuffer();
  pg.popFramebuffer();
}

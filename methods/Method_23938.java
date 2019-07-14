public void setColorBuffers(Texture[] textures,int n){
  if (screenFb)   return;
  if (numColorBuffers != PApplet.min(n,textures.length)) {
    throw new RuntimeException("Wrong number of textures to set the color " + "buffers.");
  }
  for (int i=0; i < numColorBuffers; i++) {
    colorBufferTex[i]=textures[i];
  }
  pg.pushFramebuffer();
  pg.setFramebuffer(this);
  for (int i=0; i < numColorBuffers; i++) {
    pgl.framebufferTexture2D(PGL.FRAMEBUFFER,PGL.COLOR_ATTACHMENT0 + i,PGL.TEXTURE_2D,0,0);
  }
  for (int i=0; i < numColorBuffers; i++) {
    pgl.framebufferTexture2D(PGL.FRAMEBUFFER,PGL.COLOR_ATTACHMENT0 + i,colorBufferTex[i].glTarget,colorBufferTex[i].glName,0);
  }
  pgl.validateFramebuffer();
  pg.popFramebuffer();
}

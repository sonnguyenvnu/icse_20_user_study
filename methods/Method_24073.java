protected void setFramebuffer(FrameBuffer fbo){
  PGraphicsOpenGL ppg=getPrimaryPG();
  if (ppg.currentFramebuffer != fbo) {
    ppg.currentFramebuffer=fbo;
    if (ppg.currentFramebuffer != null)     ppg.currentFramebuffer.bind();
  }
}

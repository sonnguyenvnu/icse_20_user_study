protected void endBindFramebuffer(int target,int framebuffer){
  FrameBuffer fb=getCurrentFB();
  if (framebuffer == 0 && fb != null && fb.glFbo != 0) {
    fb.bind();
  }
}

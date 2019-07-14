public FrameBuffer getFrameBuffer(boolean multi){
  if (multi) {
    return multisampleFramebuffer;
  }
 else {
    return offscreenFramebuffer;
  }
}

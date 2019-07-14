protected void pushFramebuffer(){
  PGraphicsOpenGL ppg=getPrimaryPG();
  if (ppg.fbStackDepth == FB_STACK_DEPTH) {
    throw new RuntimeException("Too many pushFramebuffer calls");
  }
  ppg.fbStack[ppg.fbStackDepth]=ppg.currentFramebuffer;
  ppg.fbStackDepth++;
}

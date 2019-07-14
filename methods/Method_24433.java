public void setFrameRate(float fps){
  if (fps < 1) {
    PGraphics.showWarning("The OpenGL renderer cannot have a frame rate lower than 1.\n" + "Your sketch will run at 1 frame per second.");
    fps=1;
  }
 else   if (fps > 1000) {
    PGraphics.showWarning("The OpenGL renderer cannot have a frame rate higher than 1000.\n" + "Your sketch will run at 1000 frames per second.");
    fps=1000;
  }
  if (animator != null) {
    animator.stop();
    animator.setFPS((int)fps);
    pgl.setFps(fps);
    animator.start();
  }
}

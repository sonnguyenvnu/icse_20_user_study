protected boolean checkGLThread(){
  if (pgl.threadIsCurrent()) {
    return true;
  }
 else {
    PGraphics.showWarning(OPENGL_THREAD_ERROR);
    return false;
  }
}

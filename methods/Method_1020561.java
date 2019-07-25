public void start(){
  mGLThread.start();
  mGLThread.surfaceCreated();
  mGLThread.onWindowResize(width,height);
  isStart=true;
}

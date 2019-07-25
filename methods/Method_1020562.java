public void end(){
  if (mGLThread != null) {
    mGLThread.requestExitAndWait();
  }
  recycleProduceTexture();
}

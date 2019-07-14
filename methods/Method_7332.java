public void awaitNewImage(){
  final int TIMEOUT_MS=2500;
synchronized (mFrameSyncObject) {
    while (!mFrameAvailable) {
      try {
        mFrameSyncObject.wait(TIMEOUT_MS);
        if (!mFrameAvailable) {
          throw new RuntimeException("Surface frame wait timed out");
        }
      }
 catch (      InterruptedException ie) {
        throw new RuntimeException(ie);
      }
    }
    mFrameAvailable=false;
  }
  mTextureRender.checkGlError("before updateTexImage");
  mSurfaceTexture.updateTexImage();
}

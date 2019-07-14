private void setupPreview(){
synchronized (mCameraLock) {
    if (mCamera != null) {
      try {
        mCamera.reconnect();
        mCamera.setPreviewDisplay(mPreview.getSurfaceHolder());
      }
 catch (      IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

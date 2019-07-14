private void adjustCameraParameters(){
synchronized (mCameraLock) {
    if (mShowingPreview) {
      mCamera.stopPreview();
    }
    adjustCameraParameters(0);
    if (mShowingPreview) {
      mCamera.startPreview();
    }
  }
}

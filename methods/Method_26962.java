@Override void stop(){
  if (mCamera != null) {
    mCamera.stopPreview();
  }
  mShowingPreview=false;
  releaseCamera();
}

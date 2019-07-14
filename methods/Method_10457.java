@Override void setDisplayOrientation(int displayOrientation){
  if (mDisplayOrientation == displayOrientation) {
    return;
  }
  mDisplayOrientation=displayOrientation;
  if (isCameraOpened()) {
    mCameraParameters.setRotation(calcCameraRotation(displayOrientation));
    mCamera.setParameters(mCameraParameters);
    final boolean needsToStopPreview=mShowingPreview && Build.VERSION.SDK_INT < 14;
    if (needsToStopPreview) {
      mCamera.stopPreview();
    }
    mCamera.setDisplayOrientation(calcDisplayOrientation(displayOrientation));
    if (needsToStopPreview) {
      mCamera.startPreview();
    }
  }
}

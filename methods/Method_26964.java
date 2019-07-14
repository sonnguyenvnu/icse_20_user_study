@Override void setDisplayOrientation(int displayOrientation){
  if (mDisplayOrientation == displayOrientation) {
    return;
  }
  mDisplayOrientation=displayOrientation;
  if (isCameraOpened()) {
    mCameraParameters.setRotation(calcCameraRotation(displayOrientation));
    mCamera.setParameters(mCameraParameters);
    mCamera.setDisplayOrientation(calcDisplayOrientation(displayOrientation));
  }
}

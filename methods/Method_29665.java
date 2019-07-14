private void adjustCameraParameters(int currentTry){
  boolean haveToReadjust=false;
  Camera.Parameters resolutionLess=mCamera.getParameters();
  if (getPreviewResolution() != null) {
    mPreview.setPreviewParameters(getPreviewResolution().getWidth(),getPreviewResolution().getHeight(),mCameraParameters.getPreviewFormat());
    mCameraParameters.setPreviewSize(getCameraPreviewResolution().getWidth(),getCameraPreviewResolution().getHeight());
    try {
      mCamera.setParameters(mCameraParameters);
      resolutionLess=mCameraParameters;
    }
 catch (    Exception e) {
      notifyErrorListener(e);
      mCameraParameters=resolutionLess;
    }
  }
 else {
    haveToReadjust=true;
  }
  if (getCaptureResolution() != null) {
    mCameraParameters.setPictureSize(getCaptureResolution().getWidth(),getCaptureResolution().getHeight());
    try {
      mCamera.setParameters(mCameraParameters);
      resolutionLess=mCameraParameters;
    }
 catch (    Exception e) {
      notifyErrorListener(e);
      mCameraParameters=resolutionLess;
    }
  }
 else {
    haveToReadjust=true;
  }
  int rotation=calculateCaptureRotation();
  mCameraParameters.setRotation(rotation);
  setFocus(mFocus);
  try {
    setFlash(mFlash);
  }
 catch (  Exception e) {
    notifyErrorListener(e);
  }
  if (mCameraParameters.isZoomSupported()) {
    setZoom(mZoom);
  }
  mCamera.setParameters(mCameraParameters);
  if (haveToReadjust && currentTry < 100) {
    try {
      Thread.sleep(1);
    }
 catch (    InterruptedException e) {
      e.printStackTrace();
    }
    notifyErrorListener(String.format("retryAdjustParam Failed, attempt #: %d",currentTry));
    adjustCameraParameters(currentTry + 1);
  }
}

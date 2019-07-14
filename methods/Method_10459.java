private void openCamera(){
  if (mCamera != null) {
    releaseCamera();
  }
  mCamera=Camera.open(mCameraId);
  mCameraParameters=mCamera.getParameters();
  mPreviewSizes.clear();
  for (  Camera.Size size : mCameraParameters.getSupportedPreviewSizes()) {
    mPreviewSizes.add(new Size(size.width,size.height));
  }
  mPictureSizes.clear();
  for (  Camera.Size size : mCameraParameters.getSupportedPictureSizes()) {
    mPictureSizes.add(new Size(size.width,size.height));
  }
  if (mAspectRatio == null) {
    mAspectRatio=Constants.DEFAULT_ASPECT_RATIO;
  }
  adjustCameraParameters();
  mCamera.setDisplayOrientation(calcDisplayOrientation(mDisplayOrientation));
  mCallback.onCameraOpened();
}

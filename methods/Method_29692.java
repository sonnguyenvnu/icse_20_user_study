public CameraSize getPhotoResolution(){
  if (mCameraPreview.getPhotoSize().area() == 0) {
    return null;
  }
  return mCameraPreview.getPhotoSize();
}

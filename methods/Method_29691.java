public CameraSize getPreviewResolution(){
  if (mCameraPreview.getPreviewSize().area() == 0) {
    return null;
  }
  return mCameraPreview.getPreviewSize();
}

public Size getCaptureSize(){
  return mCameraImpl != null ? mCameraImpl.getCaptureResolution() : null;
}

public Size getPreviewSize(){
  return mCameraImpl != null ? mCameraImpl.getPreviewResolution() : null;
}

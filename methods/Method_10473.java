@Override boolean setAspectRatio(AspectRatio ratio){
  if (ratio == null || ratio.equals(mAspectRatio) || !mPreviewSizes.ratios().contains(ratio)) {
    return false;
  }
  mAspectRatio=ratio;
  prepareImageReader();
  if (mCaptureSession != null) {
    mCaptureSession.close();
    mCaptureSession=null;
    startCaptureSession();
  }
  return true;
}

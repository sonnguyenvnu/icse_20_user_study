void adjustCameraParameters(){
  SortedSet<Size> sizes=mPreviewSizes.sizes(mAspectRatio);
  if (sizes == null) {
    mAspectRatio=chooseAspectRatio();
    sizes=mPreviewSizes.sizes(mAspectRatio);
  }
  Size size=chooseOptimalSize(sizes);
  final Size pictureSize=mPictureSizes.sizes(mAspectRatio).last();
  if (mShowingPreview) {
    mCamera.stopPreview();
  }
  mCameraParameters.setPreviewSize(size.getWidth(),size.getHeight());
  mCameraParameters.setPictureSize(pictureSize.getWidth(),pictureSize.getHeight());
  mCameraParameters.setRotation(calcCameraRotation(mDisplayOrientation));
  setAutoFocusInternal(mAutoFocus);
  setFlashInternal(mFlash);
  mCamera.setParameters(mCameraParameters);
  if (mShowingPreview) {
    mCamera.startPreview();
  }
}

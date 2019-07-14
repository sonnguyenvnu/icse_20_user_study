void adjustCameraParameters(){
  SortedSet<Size> sizes=mPreviewSizes.sizes(mAspectRatio);
  if (sizes == null) {
    mAspectRatio=chooseAspectRatio();
    sizes=mPreviewSizes.sizes(mAspectRatio);
  }
  Size size=chooseOptimalSize(sizes);
  SortedSet<Size> sizeSortedSet=mPictureSizes.sizes(mAspectRatio);
  if (sizeSortedSet == null) {
    sizeSortedSet=mPictureSizes.sizes(AspectRatio.parse("4:3"));
  }
  final Size pictureSize=sizeSortedSet.last();
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

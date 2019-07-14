Size getCameraPreviewResolution(){
  if (mPreviewSize == null && mCameraParameters != null) {
    TreeSet<Size> sizes=new TreeSet<>();
    for (    Camera.Size size : mCameraParameters.getSupportedPreviewSizes()) {
      sizes.add(new Size(size.width,size.height));
    }
    TreeSet<AspectRatio> aspectRatios=findCommonAspectRatios(mCameraParameters.getSupportedPreviewSizes(),mCameraParameters.getSupportedPictureSizes());
    AspectRatio targetRatio=null;
    if (mLockVideoAspectRatio) {
      TreeSet<AspectRatio> videoAspectRatios=findCommonAspectRatios(mCameraParameters.getSupportedPreviewSizes(),mCameraParameters.getSupportedPictureSizes());
      Iterator<AspectRatio> descendingIterator=aspectRatios.descendingIterator();
      while (targetRatio == null && descendingIterator.hasNext()) {
        AspectRatio ratio=descendingIterator.next();
        if (videoAspectRatios.contains(ratio)) {
          targetRatio=ratio;
        }
      }
    }
    if (targetRatio == null) {
      targetRatio=aspectRatios.size() > 0 ? aspectRatios.last() : null;
    }
    Iterator<Size> descendingSizes=sizes.descendingIterator();
    Size size;
    while (descendingSizes.hasNext() && mPreviewSize == null) {
      size=descendingSizes.next();
      if (targetRatio == null || targetRatio.matches(size)) {
        mPreviewSize=size;
        break;
      }
    }
  }
  return mPreviewSize;
}

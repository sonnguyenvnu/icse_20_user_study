@Override Size getCaptureResolution(){
  if (mCaptureSize == null && mCameraParameters != null) {
    TreeSet<Size> sizes=new TreeSet<>();
    for (    Camera.Size size : mCameraParameters.getSupportedPictureSizes()) {
      sizes.add(new Size(size.width,size.height));
    }
    TreeSet<AspectRatio> aspectRatios=findCommonAspectRatios(mCameraParameters.getSupportedPreviewSizes(),mCameraParameters.getSupportedPictureSizes());
    AspectRatio targetRatio=aspectRatios.size() > 0 ? aspectRatios.last() : null;
    Iterator<Size> descendingSizes=sizes.descendingIterator();
    Size size;
    while (descendingSizes.hasNext() && mCaptureSize == null) {
      size=descendingSizes.next();
      if (targetRatio == null || targetRatio.matches(size)) {
        mCaptureSize=size;
        break;
      }
    }
  }
  return mCaptureSize;
}

@Override Size getVideoResolution(){
  if (mVideoSize == null && mCameraParameters != null) {
    if (mCameraParameters.getSupportedVideoSizes() == null) {
      mVideoSize=getCaptureResolution();
      return mVideoSize;
    }
    TreeSet<Size> sizes=new TreeSet<>();
    for (    Camera.Size size : mCameraParameters.getSupportedVideoSizes()) {
      sizes.add(new Size(size.width,size.height));
    }
    TreeSet<AspectRatio> aspectRatios=findCommonAspectRatios(mCameraParameters.getSupportedPreviewSizes(),mCameraParameters.getSupportedVideoSizes());
    AspectRatio targetRatio=aspectRatios.size() > 0 ? aspectRatios.last() : null;
    Iterator<Size> descendingSizes=sizes.descendingIterator();
    Size size;
    while (descendingSizes.hasNext() && mVideoSize == null) {
      size=descendingSizes.next();
      if (targetRatio == null || targetRatio.matches(size)) {
        mVideoSize=size;
        break;
      }
    }
  }
  return mVideoSize;
}

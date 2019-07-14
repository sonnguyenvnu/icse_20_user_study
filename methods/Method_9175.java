@Override public void onVideoSizeChanged(int width,int height,int unappliedRotationDegrees,float pixelWidthHeightRatio){
  if (aspectRatioFrameLayout != null) {
    if (unappliedRotationDegrees == 90 || unappliedRotationDegrees == 270) {
      int temp=width;
      width=height;
      height=temp;
    }
    float ratio=height == 0 ? 1 : (width * pixelWidthHeightRatio) / height;
    aspectRatioFrameLayout.setAspectRatio(ratio,unappliedRotationDegrees);
    if (inFullscreen) {
      delegate.onVideoSizeChanged(ratio,unappliedRotationDegrees);
    }
  }
}

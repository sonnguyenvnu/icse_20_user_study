@Override public boolean canResize(EncodedImage encodedImage,@Nullable RotationOptions rotationOptions,@Nullable ResizeOptions resizeOptions){
  if (rotationOptions == null) {
    rotationOptions=RotationOptions.autoRotate();
  }
  return mResizingEnabled && DownsampleUtil.determineSampleSize(rotationOptions,resizeOptions,encodedImage,mMaxBitmapSize) > DownsampleUtil.DEFAULT_SAMPLE_SIZE;
}

@Override public boolean canResize(EncodedImage encodedImage,@Nullable RotationOptions rotationOptions,@Nullable ResizeOptions resizeOptions){
  if (rotationOptions == null) {
    rotationOptions=RotationOptions.autoRotate();
  }
  return JpegTranscoderUtils.getSoftwareNumerator(rotationOptions,resizeOptions,encodedImage,mResizingEnabled) < JpegTranscoderUtils.SCALE_DENOMINATOR;
}

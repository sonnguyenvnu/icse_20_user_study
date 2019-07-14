private int getSampleSize(final EncodedImage encodedImage,final RotationOptions rotationOptions,@Nullable final ResizeOptions resizeOptions){
  int sampleSize;
  if (!mResizingEnabled) {
    sampleSize=DownsampleUtil.DEFAULT_SAMPLE_SIZE;
  }
 else {
    sampleSize=DownsampleUtil.determineSampleSize(rotationOptions,resizeOptions,encodedImage,mMaxBitmapSize);
  }
  return sampleSize;
}

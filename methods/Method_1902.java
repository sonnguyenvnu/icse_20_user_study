@DoNotStrip @Override @Nullable public ImageTranscoder createImageTranscoder(ImageFormat imageFormat,boolean isResizingEnabled){
  if (imageFormat != DefaultImageFormats.JPEG) {
    return null;
  }
  return new NativeJpegTranscoder(isResizingEnabled,mMaxBitmapSize,mUseDownSamplingRatio);
}

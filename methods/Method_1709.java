@Nullable private ImageTranscoder getCustomImageTranscoder(ImageFormat imageFormat,boolean isResizingEnabled){
  if (mPrimaryImageTranscoderFactory == null) {
    return null;
  }
  return mPrimaryImageTranscoderFactory.createImageTranscoder(imageFormat,isResizingEnabled);
}

private ImageTranscoder getSimpleImageTranscoder(ImageFormat imageFormat,boolean isResizingEnabled){
  return new SimpleImageTranscoderFactory(mMaxBitmapSize).createImageTranscoder(imageFormat,isResizingEnabled);
}

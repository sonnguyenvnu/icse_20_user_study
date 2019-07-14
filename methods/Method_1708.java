@Override public ImageTranscoder createImageTranscoder(ImageFormat imageFormat,boolean isResizingEnabled){
  ImageTranscoder imageTranscoder=getCustomImageTranscoder(imageFormat,isResizingEnabled);
  if (imageTranscoder == null) {
    imageTranscoder=getImageTranscoderWithType(imageFormat,isResizingEnabled);
  }
  if (imageTranscoder == null) {
    imageTranscoder=getNativeImageTranscoder(imageFormat,isResizingEnabled);
  }
  return imageTranscoder == null ? getSimpleImageTranscoder(imageFormat,isResizingEnabled) : imageTranscoder;
}

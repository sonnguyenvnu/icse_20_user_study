private static TriState shouldTransform(ImageRequest request,EncodedImage encodedImage,ImageTranscoder imageTranscoder){
  if (encodedImage == null || encodedImage.getImageFormat() == ImageFormat.UNKNOWN) {
    return TriState.UNSET;
  }
  if (!imageTranscoder.canTranscode(encodedImage.getImageFormat())) {
    return TriState.NO;
  }
  return TriState.valueOf(shouldRotate(request.getRotationOptions(),encodedImage) || imageTranscoder.canResize(encodedImage,request.getRotationOptions(),request.getResizeOptions()));
}

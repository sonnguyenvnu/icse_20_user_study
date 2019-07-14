private static TriState shouldTranscode(final EncodedImage encodedImage){
  Preconditions.checkNotNull(encodedImage);
  ImageFormat imageFormat=ImageFormatChecker.getImageFormat_WrapIOException(encodedImage.getInputStream());
  if (DefaultImageFormats.isStaticWebpFormat(imageFormat)) {
    final WebpTranscoder webpTranscoder=WebpTranscoderFactory.getWebpTranscoder();
    if (webpTranscoder == null) {
      return TriState.NO;
    }
    return TriState.valueOf(!webpTranscoder.isWebpNativelySupported(imageFormat));
  }
 else   if (imageFormat == ImageFormat.UNKNOWN) {
    return TriState.UNSET;
  }
  return TriState.NO;
}

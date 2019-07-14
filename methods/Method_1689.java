private static void doTranscode(final EncodedImage encodedImage,final PooledByteBufferOutputStream outputStream) throws Exception {
  InputStream imageInputStream=encodedImage.getInputStream();
  ImageFormat imageFormat=ImageFormatChecker.getImageFormat_WrapIOException(imageInputStream);
  if (imageFormat == DefaultImageFormats.WEBP_SIMPLE || imageFormat == DefaultImageFormats.WEBP_EXTENDED) {
    WebpTranscoderFactory.getWebpTranscoder().transcodeWebpToJpeg(imageInputStream,outputStream,DEFAULT_JPEG_QUALITY);
    encodedImage.setImageFormat(DefaultImageFormats.JPEG);
  }
 else   if (imageFormat == DefaultImageFormats.WEBP_LOSSLESS || imageFormat == DefaultImageFormats.WEBP_EXTENDED_WITH_ALPHA) {
    WebpTranscoderFactory.getWebpTranscoder().transcodeWebpToPng(imageInputStream,outputStream);
    encodedImage.setImageFormat(DefaultImageFormats.PNG);
  }
 else {
    throw new IllegalArgumentException("Wrong image format");
  }
}

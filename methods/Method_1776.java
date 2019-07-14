public ImageFormat determineImageFormat(final InputStream is) throws IOException {
  Preconditions.checkNotNull(is);
  final byte[] imageHeaderBytes=new byte[mMaxHeaderLength];
  final int headerSize=readHeaderFromStream(mMaxHeaderLength,is,imageHeaderBytes);
  ImageFormat format=mDefaultFormatChecker.determineFormat(imageHeaderBytes,headerSize);
  if (format != null && format != ImageFormat.UNKNOWN) {
    return format;
  }
  if (mCustomImageFormatCheckers != null) {
    for (    ImageFormat.FormatChecker formatChecker : mCustomImageFormatCheckers) {
      format=formatChecker.determineFormat(imageHeaderBytes,headerSize);
      if (format != null && format != ImageFormat.UNKNOWN) {
        return format;
      }
    }
  }
  return ImageFormat.UNKNOWN;
}
